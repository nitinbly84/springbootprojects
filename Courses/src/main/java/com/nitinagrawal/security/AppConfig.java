package com.nitinagrawal.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nitinagrawal.mail.SendMailLFilter;

@Configuration
public class AppConfig {

 @Bean
 public FilterRegistrationBean < SendMailLFilter > filterRegistrationBean() {
  FilterRegistrationBean < SendMailLFilter > registrationBean = new FilterRegistrationBean<>();
  SendMailLFilter sendMailLFilter = new SendMailLFilter();

  registrationBean.setFilter(sendMailLFilter);
  registrationBean.addUrlPatterns("/*");
  registrationBean.setOrder(1); //set precedence
  return registrationBean;
 }
 
 @Bean
 public WebMvcConfigurer corsConfigurer() {
 	return new WebMvcConfigurer() {
 		@Override
 		public void addCorsMappings(CorsRegistry registry) {
 			registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
 				.allowedHeaders("*");
 		}
 	};
 }
}