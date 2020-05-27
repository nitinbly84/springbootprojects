package com.nitinagrawal.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {

	public void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers("/topics/**").hasRole("ACTUATOR")
//		.anyRequest().permitAll();
		 http.csrf().disable()
			        .authorizeRequests()
			        .requestMatchers(EndpointRequest.to(HealthEndpoint.class))
			        .permitAll()
			        .requestMatchers(EndpointRequest.toAnyEndpoint())
			        .authenticated()
			        .anyRequest().authenticated()
			        .and()
			        .httpBasic();
	}
}
