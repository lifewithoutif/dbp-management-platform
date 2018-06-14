package com.hna.dbp.error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hna.dbp.exception.UserException;
import com.hna.dbp.vo.response.RequestResult;

@RestController
public class MainsiteErrorController implements ErrorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainsiteErrorController.class);

  @Resource(name = "errorAttributes")
  private final ErrorAttributes errorAttributes;

  @Value("${system.internal.show}")
  private Boolean internalShow;

  public MainsiteErrorController(ErrorAttributes errorAttributes) {
    Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
    this.errorAttributes = errorAttributes;
  }

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping(value = "/error")
  @ResponseBody
  public ResponseEntity<RequestResult> error(HttpServletRequest request) {
    Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
    RequestResult result = new RequestResult();
    Exception exception = (Exception) body.get("exception");
    LOGGER.error("exception={}", exception.getMessage());
    String exceptionMsg = UserException.MAINTAINING;
    if (exception instanceof UserException) {
      UserException userException = (UserException) exception;
      exceptionMsg = userException.getMsg();
    }

    Map<String, List<String>> errorMsg = new HashMap<String, List<String>>();
    List<String> errorMessageList = new ArrayList<String>();
    errorMsg.put("object.error.message", errorMessageList);
    errorMessageList.add(exceptionMsg);
    result.setErrorMsg(errorMsg);
    LOGGER.error("[internalShow={}]", internalShow);
    if (internalShow && exception != null) {
      result.setInternalErrorMsg(exception.getMessage());
    }
    HttpStatus status = getStatus(request);
    if (status.is4xxClientError()) {// 如果为4错误，需要打印出来，方便快速定位问题，4开头错误为无法找到地址，一般为用户发送的请求地址错误
      LOGGER.error("打印响应的内容数据");
      for (Map.Entry<String, Object> entry : body.entrySet()) {
        if (!"errorMsg".equals(entry.getKey())) {
          LOGGER.error("[{}={}]", entry.getKey(), entry.getValue());
        }
      }
    }
    LOGGER.error("提示用户的信息={}", exception.getMessage());
    return new ResponseEntity<RequestResult>(result, status);
  }

  private Map<String, Object> getErrorAttributes(HttpServletRequest request,
      boolean includeStackTrace) {
    RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode != null) {
      try {
        return HttpStatus.valueOf(statusCode);
      } catch (Exception ex) {
      }
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }

  private boolean getTraceParameter(HttpServletRequest request) {
    String parameter = request.getParameter("trace");
    if (parameter == null) {
      return false;
    }
    return !"false".equals(parameter.toLowerCase());
  }

}
