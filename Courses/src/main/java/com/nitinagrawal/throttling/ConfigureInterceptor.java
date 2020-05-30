package com.nitinagrawal.throttling;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import io.github.bucket4j.Bucket;

@Configuration
@EnableWebMvc
public class ConfigureInterceptor implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		Refill refill = Refill.greedy(5, Duration.ofMinutes(1));
		// Refill refill = Refill.intervally(3, Duration.ofMinutes(1));
		Bandwidth limit = Bandwidth.classic(10, refill)
				                   .withInitialTokens(1);
		Bucket bucket = Bucket4j.builder()
								.addLimit(limit)
								.build();
		// One has to give all the possible URL patterns on which this limit need to be applied, using * only doesn't work here
		registry.addInterceptor(new RateLimitInterceptor(bucket, 1)).addPathPatterns("/topics", "/topics/*", "/topics/*/courses",
				                                                                     "/topics/*/courses/");

		refill = Refill.intervally(3, Duration.ofMinutes(1));
		limit = Bandwidth.classic(3, refill);
		bucket = Bucket4j.builder()
						 .addLimit(limit)
						 .build();
		registry.addInterceptor(new RateLimitInterceptor(bucket, 1)).addPathPatterns("/courses", "/courses/", "/courses/*");
	}
}
