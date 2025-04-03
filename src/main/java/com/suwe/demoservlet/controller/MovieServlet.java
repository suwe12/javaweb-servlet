package com.suwe.demoservlet.controller;

import com.alibaba.fastjson.JSON;
import com.suwe.demoservlet.entity.Movie;
import com.suwe.demoservlet.service.MovieService;
import com.suwe.demoservlet.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// 修改注解，去掉末尾的 /*
@WebServlet("/api/movies")
public class MovieServlet extends HttpServlet {
	private MovieService movieService = new MovieService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");

		try {
			// 直接获取所有电影列表
			List<Movie> allMovies = movieService.getAllMovies();
			// 添加调试日志
			response.getWriter().write(JSON.toJSONString(Result.ok(allMovies, (long) allMovies.size())));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().write(JSON.toJSONString(Result.fail("服务器内部错误：" + e.getMessage())));
		}
	}
}