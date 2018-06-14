package com.hna.dbp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hna.dbp.exception.ExceptionConstants;
import com.hna.dbp.exception.UserException;
import com.hna.dbp.vo.group.Insert;
import com.hna.dbp.vo.request.User;
import com.hna.dbp.vo.response.RequestResult;

/**
 * @author jlgan
 *
 */
@Controller
@RequestMapping("/docker")
public class TestController extends AbsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/q", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView List() {
		LOGGER.debug("controller ");
		try {
		} catch (Exception e) {
			throw new UserException(ExceptionConstants.SYSTEM_ERROR);
		}
		RequestResult re = new RequestResult("******************");
		return createModelAndView(re);
	}

	@RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView add(@Validated({ Insert.class }) @RequestBody User User, BindingResult bindingResult) {
		Map<String, List<String>> errorMsg = parseErrorMsg(bindingResult);
		RequestResult result = null;
		if (errorMsg != null) {
			result = new RequestResult();
			result.setErrorMsg(errorMsg);
			return createModelAndView(result);
		}
		try {
		} catch (Exception e) {
			throw new UserException(ExceptionConstants.SYSTEM_ERROR);
		}
		result = new RequestResult(User.getName());
		return createModelAndView(result);
	}

}
