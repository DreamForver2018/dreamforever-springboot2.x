package com.dreamforever.springboot.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dreamforever.springboot.entity.RedisUser;
import com.dreamforever.springboot.service.IRedisService;

@Service
public class RedisServiceImpl implements IRedisService{

	@Override
	@Cacheable(value = "user", key = "'user_'+#username")  
	public RedisUser getRedisUser(String username) {
		System.out.println(username + "进入实现类获取数据！");  
        return new RedisUser("Ttomm", 22);  
	}
}
