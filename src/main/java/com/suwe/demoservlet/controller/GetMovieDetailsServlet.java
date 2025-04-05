package com.suwe.demoservlet.controller;

import com.alibaba.fastjson.JSON;
import com.suwe.demoservlet.entity.Seat;
import com.suwe.demoservlet.service.SeatService;
import com.suwe.demoservlet.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getMovieDetails")
public class GetMovieDetailsServlet extends HttpServlet {
	private SeatService seatService = new SeatService();

	//查询场次
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String showingId = request.getParameter("showingId");
		response.setContentType("application/json;charset=UTF-8");

		// 确保 showingId 不为空
		if (showingId == null || showingId.isEmpty()) {
			response.getWriter().write(JSON.toJSONString(Result.fail("showingId is required")));
			return;
		}

		// 查询座位信息
		List<Seat> seatsByShowingId = seatService.findSeatsByShowingId(Long.parseLong(showingId));
		response.getWriter().write(JSON.toJSONString(Result.ok(seatsByShowingId)));
	}
}