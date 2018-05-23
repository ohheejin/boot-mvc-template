package com.template.boot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.template.boot")
@ComponentScan("com.template.boot.common.config")
public class BootMvcTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootMvcTemplateApplication.class, args);
	}
}
