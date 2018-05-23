package com.template.boot.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/main")
public class MainController {

	private Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = {"/", "/main"})
	public String init(HttpServletRequest request) {
		logger.debug("Request URI : {}", request.getRequestURI());
		return "main/main";
	}
}
