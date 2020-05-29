package com.nitinagrawal.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
@EnableWebMvc
public class TopicServiceInterceptor implements HandlerInterceptor, WebMvcConfigurer {
	@Override
	public boolean preHandle
	(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		String url = request.getRequestURI();
		if(!url.startsWith("topic"))
			return true;
		if ("PUT".equalsIgnoreCase(request.getMethod())) 
		{
			System.out.println("Put request has been made....");
		}
		System.out.println("Pre Handle method is Calling for topic.");
		System.out.println(request.getRequestURL());
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception {
		String url = request.getRequestURI();
		if(!url.startsWith("topic"))
			return;
		System.out.println("Post Handle method is Calling for topic");
	}
	@Override
	public void afterCompletion
	(HttpServletRequest request, HttpServletResponse response, Object 
			handler, Exception exception) throws Exception {

		String url = request.getRequestURI();
		if(!url.startsWith("topic"))
			return;
		System.out.println("Request and Response is completed for topic");
	}
}
