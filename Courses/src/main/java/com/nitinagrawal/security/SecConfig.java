package com.nitinagrawal.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/h2-console/**")
		.authenticated();
		http.csrf().disable()
			       .authorizeRequests()
			       .requestMatchers(EndpointRequest.to(HealthEndpoint.class))
			       .permitAll()
			       .antMatchers("/swagger-ui.html","/swagger-ui.html/*")
			       .permitAll()
			       .requestMatchers(EndpointRequest.toAnyEndpoint())
			       .authenticated()
			       .anyRequest()
			       .authenticated()
			       .and()
			       .httpBasic();
		http.headers().frameOptions().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs",
								   "/configuration/ui",
								   "/swagger-resources/**",
								   "/configuration/security",
								   "/swagger-ui.html",
								   "/webjars/**");
	}
}
