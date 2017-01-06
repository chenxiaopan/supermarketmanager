package com.hy.cxp.supermarket.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*@WebFilter(filterName="CharacterEncoding",urlPatterns={"*.do"},initParams={@WebInitParam (name="encoding",value="utf-8")})*/
public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig;
	private String characterEncoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("init---------------->");
		this.filterConfig = filterConfig;
		// 获得配置的参数值
		characterEncoding= filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("编码处理------------->");
		// 请求编码处理
		request.setCharacterEncoding(characterEncoding);
		// 调用下一个过滤器或Web资源
		chain.doFilter(request, response);


	}

	@Override
	public void destroy() {
		System.out.println("destroy-------------->");
	}

}
