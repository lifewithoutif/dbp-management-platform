package com.hna.dbp.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Object2ArrayUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(Object2ArrayUtil.class);
	/**
	 * 对象转Byte数组
	 * @param obj
	 * @return
	 */
	public static byte[] objectToByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			bytes = byteArrayOutputStream.toByteArray();

		} catch (IOException e) {
			LOGGER.error("objectToByteArray failed, " + e);
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					LOGGER.error("close objectOutputStream failed, " + e);
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException e) {
					LOGGER.error("close byteArrayOutputStream failed, " + e);
				}
			}

		}
		return bytes;
	}

	/**
	 * Byte数组转对象
	 * @param bytes
	 * @return
	 */
	public static Object byteArrayToObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			obj = objectInputStream.readObject();
		} catch (Exception e) {
			LOGGER.error("byteArrayToObject failed, " + e);
		} finally {
			if (byteArrayInputStream != null) {
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
					LOGGER.error("close byteArrayInputStream failed, " + e);
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					LOGGER.error("close objectInputStream failed, " + e);
				}
			}
		}
		return obj;
	}

}