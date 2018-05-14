package com.dreamforever.springboot.entity;

public class User {
	/**
	 * id
	 */
	private Integer id;

	/**
	 * name
	 */
	private String name;
	/**
	 * password
	 */
	private String password;
	/**
	 * phone
	 */
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
