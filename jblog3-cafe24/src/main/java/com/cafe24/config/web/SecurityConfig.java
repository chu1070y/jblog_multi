package com.cafe24.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.security.AuthInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;
import com.cafe24.security.BlogInterceptor;

@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter{


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

}
