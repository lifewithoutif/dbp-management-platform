package com.hna.dbp.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类名: ApplicationInterceptor <br/>
 * <br/>
 * 
 * @author jlgan
 *
 */
public class ApplicationInterceptor implements HandlerInterceptor {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationInterceptor.class);

	/**
	 * 在执行方法中的逻辑之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	/**
	 * 执行主体的业务逻辑之后执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			if (!(request instanceof RestHttpServletRequestWrapper)) {
				return;
			}
			Map<String, Object> map = modelAndView.getModel();
			String data = (String) map.get("data");
			String status = (String) map.get("status");
			LOG.debug("status：{}   data:{}", status, data);

		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
