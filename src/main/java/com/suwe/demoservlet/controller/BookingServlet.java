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
	private SeatService seatService = new SeatService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取信息

		try {


			Long userId = Long.parseLong(request.getParameter("userId"));
			Long movieId = Long.parseLong(request.getParameter("movieId"));
			BigDecimal price = new BigDecimal(request.getParameter("price"));
			Long showingId = Long.parseLong(request.getParameter("showingId"));


			//List<Long> seatIds = Arrays.asList(request.getParameterValues("seatIds").stream().map(Long::parseLong).toArray(Long[]::new));

			String seatIds = request.getParameter("seatIds");
			String[] seatIdsStr = seatIds.substring(1, seatIds.length() - 1).split(",");

			System.out.println("[成功] 已获取买票信息" + userId + "--movieId" + movieId + "--price" + price + "--showingId" + showingId + "--seatIds" + seatIdsStr);


			List<Long> seatIdsArr = new ArrayList<>();
			if ( seatIdsStr != null ) {
				for ( String str : seatIdsStr ) {
					long seatId = Long.parseLong(str);
					boolean b = seatService.checkSeatAvailability(seatId);
					if ( !b ) {
						response.setContentType("application/json;charset=utf-8");
						response.getWriter().write(JSON.toJSONString(Result.fail("座位已被预定")));
						System.out.println("[显示] 购票不成功，座位已被预定！");
						return;
					}
					seatIdsArr.add(seatId);
				}
			}
			System.out.println("[成功] 座位类型转换成功");

			// 调用服务层创建订单
			bookingService.createOrder(userId, movieId, price, seatIdsArr, showingId);
			System.out.println("[成功] 创建订单成功");

		} catch ( IllegalArgumentException e ) {
			e.printStackTrace();
		}

		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(Result.ok("购票成功")));
	}
}