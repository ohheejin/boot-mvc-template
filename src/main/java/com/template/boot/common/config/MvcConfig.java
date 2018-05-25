package com.template.boot.common.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	private Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		logger.debug("resource file configuring");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");
		registry.addResourceHandler("/vendor/**").addResourceLocations("/vendor/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}
	
	/**
	 * view resolver 설정
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		logger.debug("view resolver configuring");
		// 추가 순서에 따라 Order 적용됨
		
		// jsonView 설정
		registry.enableContentNegotiation(mappingJackson2JsonView());
		
		//tilesConfigurer bean을 추가해야한다.
		registry.tiles().viewClass(TilesView.class);
		
		registry.viewResolver(new BeanNameViewResolver());
		//registry.jsp("/WEB-INF/views/", ".jsp");
	}
	
	/**
	 * <pre>
	 * mappingJackson2JsonView
	 * jsonView 설정
	 * Model이나 ModelAndView로 리턴할 경우에만 연동됨.
	 * </pre>
	 *
	 * @return
	 */
	@Bean
	public MappingJackson2JsonView mappingJackson2JsonView() {
		MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
		
		// set objectMapper
		jsonView.setObjectMapper(new ObjectMapper());
		// 줄바꿈 설정
		jsonView.setPrettyPrint(true);
		// application/json content type
		jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
		// 단일 object일때 root key 제거
		jsonView.setExtractValueFromSingleKeyModel(true);
		
		// ModelMap에서 리턴할 model명 설정
		// BaseResult, SingleResult, MapResult Type만 리턴
		Set<String> modelKeys = new HashSet<String>();
		modelKeys.add("baseResult");
		modelKeys.add("mapResult");
		modelKeys.add("singleResult");

		jsonView.setModelKeys(modelKeys);
		
		return jsonView;
	}
	
	/**
	 * <pre>
	 * mappingJackson2HttpMessageConverter
	 * json Message converter
	 * Model에 포함되지 않고 @ResponseBody 등으로 바로 리턴되는 객체들에 사용
	 * </pre>
	 *
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		
		messageConverter.setObjectMapper(new ObjectMapper());
		messageConverter.setPrettyPrint(true);
		
		List<MediaType> mediaTypes = new ArrayList<MediaType>();
		mediaTypes.add(MediaType.APPLICATION_JSON);
		mediaTypes.add(MediaType.TEXT_HTML);
		
		messageConverter.setSupportedMediaTypes(mediaTypes);
		
		return messageConverter;
	}
	
	/**
	 * <pre>
	 * stringHttpMessageConverter
	 * 
	 * Request 객체의 Body를 읽어오기 위한 messageConverter
	 * </pre>
	 *
	 * @return
	 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
		
		return messageConverter;
	}
	
	/**
	 * 타일즈 설정 객체 return
	 * @return
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		String springProfileActive = System.getProperty("spring.profiles.active", "local");
		
		final TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] {"classpath:/tiles/tiles-" + springProfileActive + ".xml"});
		tilesConfigurer.setCheckRefresh(true);
		
		return tilesConfigurer;
	}
	
}
