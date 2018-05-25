package com.template.boot.application;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

public class ServletInitializer extends SpringBootServletInitializer {

	private Logger logger = LoggerFactory.getLogger(ServletInitializer.class);

	/**
	 * application.yml 경로 설정
	 * profile 별로 분리된 .yml파일은 자동으로 해당 profile로 가져온다.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.setProperty("spring.config.location", "classpath:/config/springboot/");
		
		if (System.getProperty("spring.profiles.active") == null) {
			System.setProperty("spring.profiles.active", "local");
		}

		logger.info("## spring.profiles.active : " + System.getProperty("spring.profiles.active"));
		
		return application.sources(BootMvcTemplateApplication.class);
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("## Start up");
		super.onStartup(servletContext);
	}
	
	@Override
	protected WebApplicationContext createRootApplicationContext(ServletContext servletContext) {
		logger.info("## Create Root Application Context");
		return super.createRootApplicationContext(servletContext);
	}
	
	@Override
	protected WebApplicationContext run(SpringApplication application) {
		logger.info("## Run");
		return super.run(application);
	}

}
