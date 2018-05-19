package com.dreamforever.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.dreamforever.springboot.entity.RedisUser;
import com.dreamforever.springboot.service.IRedisService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class DreamForeverApplicationTests {

	@Test
	public void contextLoads() {
	}
	
    @Autowired  
    private IRedisService redisService; 
    
    @Test  
    //使用Redis缓存对象，getUser只会被调用一次  
    public void testCache() {  
    	RedisUser user;  
        user = redisService.getRedisUser("Ttomm");  
        user = redisService.getRedisUser("Ttomm");  
        user = redisService.getRedisUser("Ttomm");  
    }  
}
