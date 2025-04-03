package com.suwe.demoservlet.entity;

public class User {
	private Long id;
	private String username;
	private String password;
	private String name;
	private String phone;

	// 构造函数
	public User() {}

	public User(Long id, String username, String password, String name, String phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	// getter和setter方法
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
}
