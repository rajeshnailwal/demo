package com.pune.hyatt.ms.core.server.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	static {
		System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
	}
	
	
	public void addResoourceHandlers(final ResourceHandlerRegistry registry){
		System.out.println("=========================================================================================Rajesh");
		registry.addResourceHandler("/*.js/**").addResourceLocations("/ui/static/js/");
		registry.addResourceHandler("/*.css/**").addResourceLocations("/ui/static/css/");
	}
	
	/*@Bean
	public FilterRegistrationBean redirectionFilterRegistration(){
		FilterRegistrationBean filter = new FilterRegistrationBean();
		filter.setName("redirection");
		return filter;
	}*/

}
