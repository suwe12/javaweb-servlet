package com.suwe.demoservlet.controller;

import com.alibaba.fastjson.JSON;
import com.suwe.demoservlet.service.BookingService;
import com.suwe.demoservlet.service.SeatService;
import com.suwe.demoservlet.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/api/bookings")
public class BookingServlet extends HttpServlet {
	private BookingService bookingService = new BookingService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取信息
		Long userId = Long.parseLong(request.getParameter("userId"));
		Long movieId = Long.parseLong(request.getParameter("movieId"));
		BigDecimal price = new BigDecimal(request.getParameter("price"));
		Long showingId = Long.parseLong(request.getParameter("showingId"));
		String seatIds = request.getParameter("seatIds");
		String[] seatIdsStr = seatIds.substring(1, seatIds.length() - 1).split(",");

		if ( userId == null || movieId == null || showingId == null || price == null || seatIdsStr == null ) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(Result.fail("无效的输入参数")));
		}
		System.out.println("[成功] 已获取买票信息" + userId + "--movieId" + movieId + "--price" + price + "--showingId" + showingId + "--seatIds" + seatIdsStr);

		// 调用服务层创建订单
		Result orderRes = bookingService.createOrder(userId, movieId, price, seatIdsStr, showingId);

		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(orderRes));

	}
}