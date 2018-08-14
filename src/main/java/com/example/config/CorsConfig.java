package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig {
	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	        
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/v2/merchant/**").allowedOrigins("http://184.72.75.108:8080");
	                registry.addMapping("/v2/product/**").allowedOrigins("http://184.72.75.108:8080");
	                registry.addMapping("/v2/store/**").allowedOrigins("http://184.72.75.108:8080");
	                registry.addMapping("/v2/brand/**").allowedOrigins("http://184.72.75.108:8080");
	                registry.addMapping("/v2/category/**").allowedOrigins("http://184.72.75.108:8080");
	            }
	        };
	    }
}