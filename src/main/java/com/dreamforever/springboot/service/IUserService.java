package com.dreamforever.springboot.service;

import com.dreamforever.springboot.entity.User;

public interface IUserService {
	/**
	 * getUserById
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
}
