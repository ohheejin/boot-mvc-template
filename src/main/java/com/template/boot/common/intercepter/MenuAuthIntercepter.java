package com.template.boot.common.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class MenuAuthIntercepter extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(MenuAuthIntercepter.class);
	
	public static void main(String[] args) {
		
	}
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		
		//chart.~~ 하면 접속되는 문제
		if (requestURI.contains(".")) {
			requestURI = requestURI.substring(0, requestURI.indexOf("."));
		}
		
		logger.debug("URI : " + requestURI);
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			
			if (requestURI.equals("/chart")) {

				if (session.getAttribute("login") == null) {
					response.sendRedirect("/");

					return false;
				}
			}
		} else {
			if (requestURI.equals("/")) {
				response.sendRedirect("/dashboard");
	
				return false;
			}
		}
		
		return true;
	}
}
