package com.xyo2o.common.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class MybatisRedisCache implements Cache{
	
	private static Logger logger = LoggerFactory.getLogger(MybatisRedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String id;
	
	public MybatisRedisCache(final String id){
		if(id == null){
			throw new IllegalArgumentException("cache instances requre an id");
		}
		logger.debug("redis缓存中的数量>>>>>>>>>>>>>>>>>>>>>>>>>>MybatisRedisCache:id"+id);
		this.id = id;
	}
	
	
	public void clear() {
		RedisUtil.getJedis().flushDB();
	}

	public String getId() {
		return this.id = id;
	}

	public Object getObject(Object key) {
		Object value = SerializeUtil.unSerialize(RedisUtil.getJedis().get(SerializeUtil.serialize(key.toString())));
		logger.debug("从缓存redis中取出数据>>>>>>>>>>>>>>>>>>>>getObject:key"+key+" = "+value);
		return value;
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public int getSize() {
		return Integer.valueOf(RedisUtil.getJedis().dbSize().toString());
	}

	public void putObject(Object key, Object value) {
		logger.debug("存入数据到redis缓存中>>>>>>>>>>>>>>>>>>>>>>>>putObject:key"+key+"  value:"+value);
		RedisUtil.getJedis().set(SerializeUtil.serialize(key.toString()),SerializeUtil.serialize(value));
	}

	public Object removeObject(Object key) {
		return RedisUtil.getJedis().expire(SerializeUtil.serialize(key.toString()),0);
	}

}
