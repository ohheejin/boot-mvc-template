package com.template.boot.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.template.boot.dashboard.service.DashboardService;

@Controller
public class DashboardController {

	private Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	private DashboardService dashboardService;
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpServletRequest request, ModelMap model) {
		logger.debug("Request URI : {}", request.getRequestURI());

		model.addAttribute("dashTest", dashboardService.selectOne());
		
		return "dashboard/dashboard";
	}
}
