package com.dreamforever.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamforever.springboot.config.RedisServiceUtil;

/**
 * 测试springboot2.0 集成的redis controller
 * 
 * @author lf199
 *
 */
@RestController
public class RedisController {
	
	@Autowired
	private RedisServiceUtil redisService;

	@RequestMapping("/setValue")
	public Integer setValue() {
      String name="setValue";
      Integer result= redisService.set("test:set", name);
      return result;
	}

	@RequestMapping("/getValue")
	public String getValue() {
		return (String) redisService.get("test:set");
	}

}
