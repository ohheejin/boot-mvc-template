package com.template.boot.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

	private Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@RequestMapping(value = "/dashboard")
	public String dashboard(HttpServletRequest request) {
		logger.debug("Request URI : {}", request.getRequestURI());
		return "dashboard/dashboard";
	}
}
