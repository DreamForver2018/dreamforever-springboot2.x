package com.dreamforever.springboot.config;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * Redis链接数据库集成各种数据类型的操作方法
 * 
 * @author lf199
 *
 */
@SuppressWarnings("unchecked")
@Configuration
public class RedisServiceUtil {

	private static final Logger logger = LoggerFactory.getLogger(RedisServiceUtil.class);

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return idOK -1: fail 1:ok
	 */
	public Integer set(final String key, Object value) {
		Integer isOk = -1;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			isOk = 1;
		} catch (Exception e) {
			logger.error("redis set method is fail", e.getMessage());

		}
		return isOk;
	}

	/**
	 * 写入缓存设置时效时间 方法重写
	 * 
	 * @param key
	 * @param value
	 * @param expireTime
	 * @return
	 */

	public Integer set(final String key, Object value, Long expireTime) {
		Integer isOk = -1;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			isOk = 1;
		} catch (Exception e) {
			logger.error("redis set method is fail", e.getMessage());
		}
		return isOk;
	}

	/**
	 * 批量删除对应的key
	 * 
	 * @param keys
	 */

	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			result = operations.get(key);
		} catch (Exception e) {
			logger.error("redis get method is fail", e.getMessage());
		}
		return result;
	}

	/**
	 * 哈希 添加
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	public Integer hmSet(String key, Object hashKey, Object value) {
		Integer isOk = -1;
		try {
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			hash.put(key, hashKey, value);
			isOk = 1;
		} catch (Exception e) {
			logger.error("redis hmSet method is fail", e.getMessage());
		}
		return isOk;
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hmGet(String key, Object hashKey) {
		Object result = null;
		try {
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			result = hash.get(key, hashKey);
		} catch (Exception e) {
			logger.error("redis hmGet method is fail", e.getMessage());
		}
		return result;
	}

	/**
	 * 列表添加
	 * 
	 * @param k
	 * @param v
	 */
	public Integer lPush(String k, Object v) {
		Integer isOk = -1;
		try {
			ListOperations<String, Object> list = redisTemplate.opsForList();
			list.rightPush(k, v);
			isOk = 1;
		} catch (Exception e) {
			logger.error("redis lPush method is fail", e.getMessage());
		}
		return isOk;
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public List<Object> lRange(String k, long l, long l1) {
		List<Object> result = null;
		try {
			ListOperations<String, Object> list = redisTemplate.opsForList();
			result = list.range(k, l, l1);
		} catch (Exception e) {
			logger.error("redis lRange method is fail", e.getMessage());
		}
		return result;
	}

	/**
	 * 集合添加
	 * 
	 * @param key
	 * @param value
	 */
	public Integer add(String key, Object value) {
		Integer isOk = -1;
		try {
			SetOperations<String, Object> set = redisTemplate.opsForSet();
			set.add(key, value);
			isOk = 1;
		} catch (Exception e) {
			logger.error("redis add method is fail", e.getMessage());
		}
		return isOk;
	}

	/**
	 * 集合获取
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> setMembers(String key) {
		Set<Object> result = null;
		try {
			SetOperations<String, Object> set = redisTemplate.opsForSet();
			result = set.members(key);
		} catch (Exception e) {
			logger.error("redis setMembers method is fail", e.getMessage());
		}
		return result;
	}

	/**
	 * 有序集合添加
	 * 
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public Integer zAdd(String key, Object value, double scoure) {
		Integer isOk = -1;
		try {
			ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
			zset.add(key, value, scoure);
			isOk = 1;
		} catch (Exception e) {
			logger.error("redis zAdd method is fail", e.getMessage());
		}
		return isOk;
	}

	/**
	 * 有序集合获取
	 * 
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
		Set<Object> result=null;
		try {
			ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
			result= zset.rangeByScore(key, scoure, scoure1);
		} catch (Exception e) {
			logger.error("redis zAdd method is fail", e.getMessage());
		}
		return result;
	}
}
