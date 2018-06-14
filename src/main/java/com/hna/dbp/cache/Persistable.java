package com.hna.dbp.cache;

/**
 * 可持久化的
 * @author ganjl
 */
public interface Persistable<K, V> {

	/**
	  * 
	  * 方法说明： 自定义实现  缓存获取不到时   查询方法
	  * @param k
	  * @return
	  * @throws Exception   
	  * V
	  * <pre>
	  *
	  * </pre>
	  */
	V load(K k) throws Exception;

	// /**
	// * 将对象序列号到数据库
	// * @param k
	// * @param v
	// * @throws PersistenceException
	// */
	// void save(K k, V v) throws Exception;

}
