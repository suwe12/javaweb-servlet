package com.suwe.demoservlet.controller;

import com.suwe.demoservlet.service.UserService;
import com.suwe.demoservlet.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import com.alibaba.fastjson.JSON;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应类型
		response.setContentType("application/json;charset=utf-8");

		// 获取请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 参数验证
		if (username == null || password == null ||
				username.trim().isEmpty() || password.trim().isEmpty()) {
			response.getWriter().write(
					JSON.toJSONString(Result.fail("用户名和密码不能为空"))
			);
			return;
		}

		// 调用service处理登录
		Result result = userService.login(username, password);

		// 如果登录成功，设置session
		if (result.getSuccess()) {
			HttpSession session = request.getSession();
			session.setAttribute("user", result.getData());
		}



		// 返回结果
		response.getWriter().write(JSON.toJSONString(result));
	}
}