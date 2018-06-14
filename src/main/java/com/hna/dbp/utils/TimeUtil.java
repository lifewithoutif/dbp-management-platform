package com.hna.dbp.utils;

public class TimeUtil {
	/** 一毫秒 */
	public static final long ONE_MILLSECOND = 1L;
	/** 一秒的毫秒数 */
	public static final long ONE_SECOND = ONE_MILLSECOND * 1000;
	/** 一分的毫秒数 */
	public static final long ONE_MINUTE = ONE_SECOND * 60;
	/** 一时的毫秒数 */
	public static final long ONE_HOUR = ONE_MINUTE * 60;
	/** 一天的毫秒数 */
	public static final long ONE_DAY = ONE_HOUR * 24;

	/**
	 * 毫秒只转成字符串的天时分秒（如:1天1小时1分钟10秒）
	 * @param ms 毫秒值（long）
	 * @return String
	 */
	public static String mstoString(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (minute > 0) {
			sb.append(minute + "分钟");
		}
		if (second > 0) {
			sb.append(second + "秒");
		}
		// if(milliSecond > 0) {
		// sb.append(milliSecond+"毫秒");
		// }
		return sb.toString();

	}
}
