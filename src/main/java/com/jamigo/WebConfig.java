package com.jamigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private AdminLoginInterceptor adminLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/counter/**")
                .addPathPatterns("/promotion/promotion4counter/*****")
                .excludePathPatterns("/counter/login/**");
        registry.addInterceptor(adminLoginInterceptor)
        		.addPathPatterns("/platform/*****") // 設置需要攔截的 URL
        		.excludePathPatterns("/platform/login/**"); 
    }
    

}
