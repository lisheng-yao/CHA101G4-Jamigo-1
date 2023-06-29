//package com.jamigo.platform.platformLogin.filter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class AdminFilterConfig implements WebMvcConfigurer {
//	
//	@Autowired
//	AdminLoginInterceptor adminLoginInterceptor;
//	
//	@Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(adminLoginInterceptor)
//                .addPathPatterns("/platform/*****") // 設置需要攔截的 URL
//                .excludePathPatterns("/platform/login/**"); 
//    }
//	
//}
