package com.cafe24.jblog.config;


import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.security.AuthInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;
import com.cafe24.security.BlogInterceptor;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	//
	// Message Converter
	//
	@Bean
	public MappingJackson2HttpMessageConverter mapping() {
		
		Jackson2ObjectMapperBuilder builder = 
				new Jackson2ObjectMapperBuilder()
				.indentOutput(true)
				.dateFormat(new SimpleDateFormat("yyy-MM-dd"))
				.modulesToInstall(new ParameterNamesModule());
		
		MappingJackson2HttpMessageConverter converter = 
				new MappingJackson2HttpMessageConverter(builder.build());
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("application", "json", Charset.forName("UTF-8"))));
		
		return converter;
	}
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter();
		converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "html", Charset.forName("UTF-8"))));
		
		return converter;
	}
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mapping());
		converters.add(stringHttpMessageConverter());
	}
	
	//
	// Interceptor
	//
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(authLoginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
		.addInterceptor(authLogoutInterceptor())
		.addPathPatterns("/user/logout");
		
		registry
		.addInterceptor(blogInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/**")
		.excludePathPatterns("/error/**")
		.excludePathPatterns("/assets/**");
		
		registry
		.addInterceptor(authInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/**")
		.excludePathPatterns("/error/**")
		.excludePathPatterns("/assets/**");
	}
	
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}
	
	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}
	
	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Bean
	public BlogInterceptor blogInterceptor() {
		return new BlogInterceptor();
	}
	
	// 파일업로드 저장 경로
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:./D:/jblog-upload/");
	}
}
