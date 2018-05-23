package com.template.boot.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	private Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("resource file configuring");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/image/**").addResourceLocations("/image/");
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		logger.debug("view resolver configuring");
		//registry.tiles().viewClass(TilesView.class);
		registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	/*@Bean
	public TilesConfigurer tilesConfigurer() {
		String springProfilesActive = System.getProperty("spring.profiles.active", "local");
		 TilesConfigurer tilesConfigurer = new TilesConfigurer();
		 tilesConfigurer.setDefinitions(new String[] {});
	}*/
	
	@Bean
	public WebContentInterceptor webContentInterceptor() {
		WebContentInterceptor interceptor = new WebContentInterceptor();
		interceptor.setCacheSeconds(0);
		interceptor.setUseExpiresHeader(true);
		interceptor.setUseCacheControlHeader(true);
		interceptor.setUseCacheControlNoStore(true);
		interceptor.setAlwaysMustRevalidate(true);
		
		return interceptor;
	}
}
