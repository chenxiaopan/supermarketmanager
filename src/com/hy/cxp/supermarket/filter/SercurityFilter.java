package com.hy.cxp.supermarket.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SercurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("登录过滤------->");
		// 转换成有协议的请求对象
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// 获得会话对象
		HttpSession session = httpServletRequest.getSession();
		if (session.getAttribute("name") != null) {
			// 进行下一个过滤链
			chain.doFilter(request, response);
		} else {
            // 返回登录页面
			httpServletResponse.sendRedirect("/supermarketmanager1115/login.html"); 
		}

	}

	@Override
	public void destroy() {

	}

}
