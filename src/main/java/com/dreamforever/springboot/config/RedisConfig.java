package com.dreamforever.springboot.config;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

/**
 * 
 * 
 * @author lf199 Redis配置类 添加cache的配置类
 */
@EnableCaching // 开启缓存的项目支持缓存
@Configuration 
@ConditionalOnClass(RedisOperations.class)  
@EnableConfigurationProperties(RedisProperties.class)  
@SuppressWarnings("rawtypes")
public class RedisConfig extends CachingConfigurerSupport {
	/**
	 * 自定义Key生成策咯
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
		@Override
		public Object generate(Object target, Method method, Object... params) {
				StringBuffer sb = new StringBuffer();
				sb.append(target.getClass().getName());
				sb.append(method.getName());
				for (Object obj : params) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}

		};
	}

	/**
	 * 序列化 redisTemplate
	 * 
	 * @param redisConnectionFactory
	 * @return
	 */
	@Bean(name = "redisTemplate")  
    @SuppressWarnings("unchecked") 
	
    @ConditionalOnMissingBean(name = "redisTemplate") 
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();  
		//使用fastjson序列化  
		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);  
        // value值的序列化采用fastJsonRedisSerializer  
        template.setValueSerializer(fastJsonRedisSerializer);  
        template.setHashValueSerializer(fastJsonRedisSerializer);  
        // key的序列化采用StringRedisSerializer  
        template.setKeySerializer(new StringRedisSerializer());  
        template.setHashKeySerializer(new StringRedisSerializer());  
  
        template.setConnectionFactory(redisConnectionFactory);  
        return template;  
	}

	//缓存管理器  
    @Bean  
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {  
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager  
                .RedisCacheManagerBuilder  
                .fromConnectionFactory(redisConnectionFactory);  
        return builder.build();  
    }  
}
