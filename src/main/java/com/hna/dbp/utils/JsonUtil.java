package com.hna.dbp.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
	public static ObjectMapper objectMapper;
	/**
	 * 将对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static final String toJson(Object obj) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream);
			jsonGenerator.writeObject(obj);
			String json = new String(outputStream.toByteArray());
			return json;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;

	}

	@SuppressWarnings("rawtypes")
	public static final HashMap toRequestResult(String json) {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.readValue(json, HashMap.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 新增泛型方法将json转为对象
	 * 
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {

		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
