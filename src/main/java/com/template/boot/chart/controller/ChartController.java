package com.template.boot.chart.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.template.boot.chart.service.ChartService;

@Controller
public class ChartController {

	@Autowired
	private ChartService chartService;
	
	@RequestMapping("/chart")
	public String chart(HttpServletRequest request, ModelMap model) {
		model.addAttribute("chartTest", chartService.selectOne());
		
		return "chart/chart";
	}
}
