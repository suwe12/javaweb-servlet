package com.suwe.demoservlet.service;

import com.suwe.demoservlet.dao.UserDao;
import com.suwe.demoservlet.entity.User;
import com.suwe.demoservlet.utils.Result;

public class UserService {
	private UserDao userDao = new UserDao();

	public Result login(String username, String password) {
		User user = userDao.findByUsername(username);
		if (user == null) {
			return Result.fail("用户不存在");
		}
		if (!user.getPassword().equals(password)) {
			return Result.fail("密码错误");
		}
		// 密码正确，清除密码信息再返回
		user.setPassword(null);
		return Result.ok(user);
	}
}