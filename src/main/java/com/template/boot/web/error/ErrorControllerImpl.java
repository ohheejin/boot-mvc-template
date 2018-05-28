package com.template.boot.web.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorControllerImpl implements ErrorController{

	@RequestMapping("/makeError")
	public String makeErorr() throws Exception {
		throw new Exception();
	}
	
	@RequestMapping("/error404")
	public String error404() {
		System.out.println("404임");
		return "error/error404";
	}
	
	@RequestMapping("/error500")
	public String error500() {
		System.out.println("500임");
		return "error/error500";
	}
	
	@RequestMapping("/error")
	public String error(HttpServletRequest request) {
		int status = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (status == HttpStatus.NOT_FOUND.value()) {
			System.out.println("404!");
			return "error/error404";
		} else {
			System.out.println("500!");
			return "error/error500";
		}
	}

	@Override
	public String getErrorPath() {
		return "redirect:/error";
	}
}
