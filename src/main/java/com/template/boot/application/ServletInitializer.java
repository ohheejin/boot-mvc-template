package com.template.boot.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	private Logger logger = LoggerFactory.getLogger(ServletInitializer.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("spring.config.location", "classpath:/springboot/");

		if (System.getProperty("spring.profiles.active") == null) {
			System.setProperty("spring.profiles.active", "local");
		}
		
		logger.info("## spring.profiles.active : " + System.getProperty("spring.profiles.active"));
		
		return application.sources(BootMvcTemplateApplication.class);
	}

}
