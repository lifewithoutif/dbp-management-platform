package com.hna.dbp.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;

import com.hna.dbp.exception.UserException;

/**
 * 用来定义统一处理异常信息的对象，负责将发生异常的请求转发的/error统一处理
 * @author jlgan
 *
 */
@Configuration("ExceptionResolverConfiguration")
public class ExceptionResolverConfiguration {
	
	
	/**
	 * 创建错误对象的属性信息，包含了错误发生时间、错误状态码、错误信息、请求地址
	 * 
	 */
	@Bean
	public DefaultErrorAttributes errorAttributes() {
		DefaultErrorAttributes defaultErrorAttributes = new SystemErrorAttributes();
		return defaultErrorAttributes;
	}
	/**
	 * 用来创建错误请求处理对象，并将错误信息传递到请求中
	 * @param errorAttributes 错误信息
	 * @return 请求处理对象
	 */
	
	private class SystemErrorAttributes extends DefaultErrorAttributes{
		@Override
		public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
				boolean includeStackTrace) {
			Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
			errorAttributes.put("timestamp", new Date());//错误发生的时间戳
			addStatus(errorAttributes, requestAttributes);//错误发生状态
			//添加错误的详细信息，包含异常类、用户自定义的异常信息
			addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
			//添加URL请求的地址
			addPath(errorAttributes, requestAttributes);
			return errorAttributes;
		}
		
		/**
		 * 添加请求的状态码
		 * @param errorAttributes 错误属性，用来存放数据
		 * @param requestAttributes 请求对象的属性
		 */
		private void addStatus(Map<String, Object> errorAttributes,
				RequestAttributes requestAttributes) {
			Integer status = getAttribute(requestAttributes,
					"javax.servlet.error.status_code");
			if (status == null) {
				errorAttributes.put("status", 999);
				errorAttributes.put("error", "None");
				return;
			}
			errorAttributes.put("status", status);
			try {
				errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
			}
			catch (Exception ex) {
				errorAttributes.put("error", "Http Status " + status);
			}
		}
		
		/**
		 * 添加错误的详细信息
		 * @param errorAttributes 
		 * @param requestAttributes
		 * @param includeStackTrace
		 */
		private void addErrorDetails(Map<String, Object> errorAttributes,
				RequestAttributes requestAttributes, boolean includeStackTrace) {
			Throwable error = getError(requestAttributes);
			if (error != null) {
				while (error instanceof ServletException && error.getCause() != null) {
					error = ((ServletException) error).getCause();
				}
				errorAttributes.put("exception", error);
				
				addErrorMessage(errorAttributes, error);
				if (includeStackTrace) {
					addStackTrace(errorAttributes, error);
				}
			}else{
				errorAttributes.put("errorMsg", UserException.MAINTAINING);
			}
			Object message = getAttribute(requestAttributes, "javax.servlet.error.message");
			if ((!StringUtils.isEmpty(message) || errorAttributes.get("message") == null)
					&& !(error instanceof BindingResult)) {
				errorAttributes.put("message",
						StringUtils.isEmpty(message) ? "No message available" : message);
			}
		}
		
		// 添加请求的地址
		private void addPath(Map<String, Object> errorAttributes,
				RequestAttributes requestAttributes) {
			String path = getAttribute(requestAttributes, "javax.servlet.error.request_uri");
			if (path != null) {
				errorAttributes.put("path", path);
			}
		}
		
		@SuppressWarnings("unchecked")
		private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
			return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
		}
		
		private void addErrorMessage(Map<String, Object> errorAttributes, Throwable error) {
			if (!(error instanceof BindingResult)) {
				errorAttributes.put("message", error.getMessage());
				return;
			}
			BindingResult result = (BindingResult) error;
			if (result.getErrorCount() > 0) {
				errorAttributes.put("errors", result.getAllErrors());
				errorAttributes.put("message",
						"Validation failed for object='" + result.getObjectName()
								+ "'. Error count: " + result.getErrorCount());
			}
			else {
				errorAttributes.put("message", "No errors");
			}
		}
		//将错误栈添加到错误属性对象中，并没有将给信息返回到页面
		private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
			StringWriter stackTrace = new StringWriter();
			error.printStackTrace(new PrintWriter(stackTrace));
			stackTrace.flush();
			errorAttributes.put("trace", stackTrace.toString());
		}
		
	}
	
}
