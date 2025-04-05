package com.suwe.demoservlet.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.suwe.demoservlet.entity.Seat;
import com.suwe.demoservlet.entity.Showing;
import com.suwe.demoservlet.service.MovieService;
import com.suwe.demoservlet.service.SeatService;
import com.suwe.demoservlet.service.ShowingService;
import com.suwe.demoservlet.utils.Result;


@WebServlet("/buy")
public class MovieDetailsServlet extends HttpServlet {

	private ShowingService showingService = new ShowingService();
	private MovieService movieService = new MovieService();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieId = request.getParameter("movieId");
		response.setContentType("application/json;charset=UTF-8");

		// 查询电影信息
		Result movieById = movieService.getMovieById(Long.parseLong(movieId));
		List<Showing> showings = showingService.getShowings(Long.parseLong(movieId));

		// 创建一个合并的结果对象
		Map<String, Object> result = new HashMap<>();
		result.put("success", true);
		result.put("data", movieById); // 确保 movieById 包含 title 和 price 字段
		result.put("showings", showings);

		// 返回合并的结果
		response.getWriter().write(JSON.toJSONString(result));
	}


}