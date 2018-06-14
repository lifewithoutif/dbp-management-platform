package com.hna.dbp.vo.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 请求结果，用户发出请求后，给用户的反馈对象， 最终以json接口的形式，提供给client
 * 
 * @author jlgan
 *
 */
public class RequestResult {

	private static final String STATUS_SUCCESS = "SUCCESS";

	private static final String STATUS_FAILURE = "FAILURE";

	private String status = STATUS_SUCCESS;// default value

	private String data; // 涉及到加密，需要先将data转换成json

	private String internalErrorMsg; // 真实的错误信息

	private Map<String, List<String>> errorMsg;// 提示给用户的错误信息

	public RequestResult() {
		data = "";
	}

	public RequestResult(Object dataObj) {
		this.status = STATUS_SUCCESS;
		String json = toJson(dataObj);
		this.data = json;
	}

	public String getStatus() {
		return status;
	}

	public String getData() {
		return data;
	}

	public void setDataObj(Object dataObj) {
		this.status = STATUS_SUCCESS;
		String json = toJson(dataObj);
		this.data = json;
	}

	public void setData(String data) {
		this.status = STATUS_SUCCESS;
		this.data = data;
	}

	public Map<String, List<String>> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, List<String>> errorMsg) {
		this.status = STATUS_FAILURE;
		this.errorMsg = errorMsg;
	}

	public String getInternalErrorMsg() {
		return internalErrorMsg;
	}

	public void setInternalErrorMsg(String internalErrorMsg) {
		this.internalErrorMsg = internalErrorMsg;
	}

	private final String toJson(Object obj) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
			jsonGenerator.writeObject(obj);
			String json = new String(outputStream.toByteArray());
			return json;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
	}

}
