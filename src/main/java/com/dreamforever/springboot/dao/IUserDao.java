package com.dreamforever.springboot.dao;

import com.dreamforever.springboot.entity.User;

public interface IUserDao {
	/**
	 * getUserById
	 * @param id
	 * @return
	 */
	User getUserById(Integer id);
}
