package com.dreamforever.springboot.service;

import com.dreamforever.springboot.entity.RedisUser;

public interface IRedisService {
   RedisUser getRedisUser(String username);
}
