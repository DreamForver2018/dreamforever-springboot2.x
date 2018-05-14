package com.dreamforever.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dreamforever.springboot.dao.IUserDao;
import com.dreamforever.springboot.entity.User;
import com.dreamforever.springboot.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public User getUserById(Integer id) {
		
		System.out.println(userDao.getUserById(1));
		return userDao.getUserById(1);
	}

}
