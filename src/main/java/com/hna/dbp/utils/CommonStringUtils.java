package com.hna.dbp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonStringUtils {

	private static final String TOTAL_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final String TOTAL_COUPON_STRING = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为浮点数（含整数）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isFloatNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]+\\.*[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为yyyyMMdd的日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isDateyyyyMMdd(String date) {
		String regex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(date);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	/**
	 * 生成32位UUID(无横线)
	 * 
	 * @return 没有中间分割线的uuid
	 */
	public static final String createUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 生成36位UUID(原生)
	 * 
	 * @return 原生UUID
	 */
	public static final String createUUID36() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 将字符串数组拆分成二维数组
	 * 
	 * @param arr
	 *            原字符串数组
	 * @param n
	 *            二维数组中，每个数组元素中的个数
	 * @return 拆分完成后的一个二维数组
	 */
	public static final String[][] split4Array(String[] arr, int n) {
		if (arr == null)
			return new String[][] { {} };
		if (n <= 0) {
			throw new IllegalArgumentException("n can not less than zero or equal zero!!");
		}
		int total = arr.length;
		int m = total / n;
		if ((total % n) != 0) {
			m++;
		}
		String[][] result = new String[m][n];
		;
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				int pointer = i * n + j;
				if (pointer < total) {
					result[i][j] = arr[pointer];
				}
			}
		}
		return result;
	}

	/**
	 * 将字符串数组拆分成二维数组,默认每个数组中有100个元素
	 * 
	 * @param arr
	 *            原字符串数组
	 * @return 拆分完成后的一个二维数组
	 */
	public static final String[][] split4Array(String[] arr) {
		return split4Array(arr, 100);
	}

	public static final List<List<String>> split4List(List<String> list, int n) {
		String[] arr = list.toArray(new String[] {});
		String[][] tmp = split4Array(arr, n);
		List<List<String>> result = new ArrayList<List<String>>();
		for (String[] item : tmp) {
			result.add(Arrays.asList(item));
		}
		return result;
	}

	public static final List<List<String>> split4List(List<String> list) {
		return split4List(list, 100);
	}

	/**
	 * 生成随机数
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(TOTAL_STRING.length());// [0,62)
			sb.append(TOTAL_STRING.charAt(number));
		}
		return sb.toString();
	}

	public static final String genCouponCode(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; ++i) {
			int number = random.nextInt(TOTAL_COUPON_STRING.length());// [0,62)
			sb.append(TOTAL_COUPON_STRING.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 生成MD5
	 * 
	 * @param pwd
	 * @param saltfigure
	 * @return
	 */
	public static String calMD5(String pwd, String saltfigure) {
		String saltPassword = pwd + saltfigure;
		String md5 = DigestUtils.md5Hex(saltPassword);
		return md5;
	}

	/**
	 * 返回正确的url链接
	 * 
	 * @param url
	 * @return
	 */
	public static String urlChecking(String url) {
		Integer hLinkType = null;
		if (url.indexOf("ttp:/") > 0) {
			hLinkType = 0;
			url = url.replaceAll("http://", "");
		}
		if (url.indexOf("ttps:/") > 0) {
			hLinkType = 1;
			url = url.replaceAll("https://", "");
		}
		if (hLinkType != null) {
			url = hLinkType == 0 ? "http://" + url : "https://" + url;
		}
		return url;
	}

}
