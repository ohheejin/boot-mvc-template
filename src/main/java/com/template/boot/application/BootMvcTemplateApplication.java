package com.template.boot.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.template.boot")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) //<- 안하면 DataSource setup에서 url이 없다고 뜸
public class BootMvcTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootMvcTemplateApplication.class, args);
	}
	
	@Value("${datasource.sv.data.jndiName}")
	private String svJndiName;
	
	@PostConstruct
	public void a() {
		//테스트용이라 logger 사용 x 삭제
		//TODO 삭제
		System.out.println(svJndiName);
	}
}
