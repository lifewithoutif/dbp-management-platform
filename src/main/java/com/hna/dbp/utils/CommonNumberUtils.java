package com.hna.dbp.utils;

/**
 * 封装数字相关的公用方法
 * 
 * @author jlgan
 *
 */
public class CommonNumberUtils {
	/**
	 * 将pageIndex转换成记录开始位置，该方法主要用于数据库的分页查询
	 * 
	 * @param pageIndex
	 *            页码，从0开始
	 * @param pageSize
	 *            每页记录条数
	 * @return 记录开始位置
	 */
	public static final int pageIndex2RecordStart(int pageIndex, int pageSize) {
		if (pageIndex < 0)
			throw new IllegalArgumentException("pageIndex 不能小于0");
		if (pageSize <= 0)
			throw new IllegalArgumentException("pageSize 不能小于等于0");
		return pageIndex * pageSize;
	}
}
