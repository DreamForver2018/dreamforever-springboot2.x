package com.dreamforever.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dreamforever.springboot.entity.User;
import com.dreamforever.springboot.service.IUserService;

@RestController
public class HomeController {

	@Autowired
	private IUserService userService;

	@RequestMapping("/toHome")
	public String toHome() {
		return "welcome my boot home";
	}

	@RequestMapping("/getUserById")
	public User getUserById() {
		System.out.println(userService.getUserById(1));
		return userService.getUserById(1);
	}
}
