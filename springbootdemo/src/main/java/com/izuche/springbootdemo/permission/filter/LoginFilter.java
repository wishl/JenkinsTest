package com.izuche.springbootdemo.permission.filter;
//package com.izuche.springboot_model.permission.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//@WebFilter(filterName="LoginFilter",urlPatterns="/*")
//public class LoginFilter implements Filter {
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//			throws IOException, ServletException {
//		HttpServletResponse response=(HttpServletResponse)res;
//		response.setHeader("Access-Control-Allow-Origin", "http://192.168.1.103:5500");
//		response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
//		response.setHeader("Access-Control-Max-Age", "3600");
//		response.setHeader("Access-Control-Allow-Headers", "Origin,Accept,X-Requested-With,Cache-Control,Content-Language,Content-Type,Expires,Last-Modified,Pragma");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
//		chain.doFilter(req, res);
//
//	}
//
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
