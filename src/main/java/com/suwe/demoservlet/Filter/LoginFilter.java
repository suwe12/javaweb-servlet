package com.suwe.demoservlet.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {"/*"})  // 修改这里，拦截所有请求
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 获取请求路径
		String path = req.getRequestURI().substring(req.getContextPath().length());

		// 如果是登录页面或登录请求，直接放行
		if (path.equals("/login.html") || path.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}

		// 检查session中是否有用户信息
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			// 未登录，重定向到登录页
			resp.sendRedirect(req.getContextPath() + "/login.html");
			return;
		}

		// 已登录，继续访问
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}