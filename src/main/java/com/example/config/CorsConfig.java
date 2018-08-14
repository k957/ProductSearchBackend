package com.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig{
	  @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	        
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("**/v2/**").allowedOrigins("http://184.72.75.108:8080/**","http://172.18.73.160:4200/**","http://172.18.73.160:8080/**")
	                .allowedMethods("GET, POST, DELETE, PUT, OPTIONS, HEAD")
	                .allowedHeaders("Content-Type, Accept, X-Requested-With");
	            }
	        };
	    }

	

	
}
