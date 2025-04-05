package com.suwe.demoservlet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/buyTicket")
public class BuyTicketServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取电影ID
		String movieId = request.getParameter("movieId");
		System.out.println("[成功] 购票页面获取电影movieId:"+movieId);

		// 将电影ID传递到购票页面
		response.sendRedirect("buyTicket.html?movieId=" + movieId);
	}
}