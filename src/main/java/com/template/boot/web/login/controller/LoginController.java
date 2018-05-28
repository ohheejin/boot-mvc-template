package com.template.boot.web.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.getSession().setAttribute("login", "Y");
		
		return "redirect:/dashboard";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		
		return "redirect:/dashboard";
	}
}
