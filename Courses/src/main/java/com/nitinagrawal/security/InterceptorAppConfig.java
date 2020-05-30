package com.nitinagrawal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorAppConfig implements WebMvcConfigurer {
	   @Autowired
	   TopicServiceInterceptor topicServiceInterceptor;
	   @Autowired
	   CourseServiceInterceptor courseServiceInterceptor;

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(topicServiceInterceptor);
	      registry.addInterceptor(courseServiceInterceptor);
	   }
	}
