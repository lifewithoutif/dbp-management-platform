package com.hna.dbp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.hna.dbp.vo.response.RequestResult;

/**
 * @author jlgan
 *
 */
public abstract class AbsController {

	/**
	 * 解析错误,将错误信息封装成Map对象<br>
	 * 
	 * <pre>
	 * {
	 *   "fieldName1":[errorMessage1,errorMessage2],
	 *   "fieldName2":[errorMessage3,errorMessage4],
	 *   "object.error.message":[errorMessage5,errorMessage6]
	 * }
	 * </pre>
	 * 
	 * map中会包含全部的错误信息，针对每个字段，可以后多个错误，用List表示<br>
	 * 针对对象的错误信息，放到object.error.message中<br>
	 * 如果没有错误信息，直接返回null<br>
	 * 
	 * @param bindingResult
	 *            绑定的错误对象<br>
	 * @return 封装到Map中的对象<br>
	 */
	protected Map<String, List<String>> parseErrorMsg(BindingResult bindingResult) {

		Map<String, List<String>> errorMsg = null;

		if (bindingResult.hasErrors()) {
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			errorMsg = new HashMap<String, List<String>>();
			for (ObjectError error : allErrors) {

				// 循环遍历错误对象，如果是字段的错误，将错误信息与字段进行对应
				// 否则，与键值object.error.message进行对应
				if (error instanceof FieldError) {
					FieldError fieldError = (FieldError) error;
					String fieldName = fieldError.getField();
					List<String> errorMessage = errorMsg.get(fieldName);
					if (errorMessage == null) {
						errorMessage = new ArrayList<String>();
						errorMsg.put(fieldName, errorMessage);
					}
					errorMessage.add(fieldError.getDefaultMessage());
				} else {

					String message = error.getDefaultMessage();
					List<String> errorMessage = errorMsg.get("object.error.message");

					if (errorMessage == null) {
						errorMessage = new ArrayList<String>();
						errorMsg.put("object.error.message", errorMessage);
					}
					errorMessage.add(message);
				}
			}
		}
		return errorMsg;
	}

	/**
	 * 给客户端的响应
	 * 
	 * @param requestResult
	 * @return
	 */
	protected ModelAndView createModelAndView(RequestResult requestResult) {
		View view = new MappingJackson2JsonView();
		ModelAndView mav = new ModelAndView();
		mav.setView(view);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (requestResult.getData() != null) {
			modelMap.put("data", requestResult.getData());
		}
		if (requestResult.getErrorMsg() != null) {
			modelMap.put("errorMsg", requestResult.getErrorMsg());
		}
		if (requestResult.getInternalErrorMsg() != null) {
			modelMap.put("internalErrorMsg", requestResult.getInternalErrorMsg());
		}

		modelMap.put("status", requestResult.getStatus());

		mav.addAllObjects(modelMap);
		return mav;
	}

	/**
	 * 
	 * 功能: 验证VO绑定错误<br/>
	 * 说明: 验证VO绑定信息是否有错误<br/>
	 * 使用示例:<br/>
	 * BindingResultHolder brHolder = new BindingResultHolderImpl();<br/>
	 * if(validateBindingError(bindingResult,brHolder)){<br/>
	 * return brHolder.getModelAndView();<br/>
	 * }<br/>
	 *
	 * @param bindingResult
	 * @param bindingResultHolder
	 *            绑定信息验证结果持有器
	 * 
	 * @return boolean true：信息验证有误；false:信息验证无误
	 *
	 */
	protected boolean validateBindingError(BindingResult bindingResult, BindingResultHolder bindingResultHolder) {
		Map<String, List<String>> errorMsg = parseErrorMsg(bindingResult);
		RequestResult result = null;
		if (errorMsg != null) {
			result = new RequestResult();
			result.setErrorMsg(errorMsg);
			bindingResultHolder.setModelAndView(createModelAndView(result));
			return true;
		}
		return false;
	}

	public interface BindingResultHolder {
		/**
		 * 获取ModelAndView对象
		 * 
		 * @return
		 */
		public ModelAndView getModelAndView();

		public void setModelAndView(ModelAndView modelAndView);

	}

	class BindingResultHolderImpl implements BindingResultHolder {

		private ModelAndView modelAndView;

		@Override
		public ModelAndView getModelAndView() {
			return modelAndView;
		}

		@Override
		public void setModelAndView(ModelAndView modelAndView) {
			this.modelAndView = modelAndView;
		}
	}
}
