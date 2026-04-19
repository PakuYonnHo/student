package com.yjb.business.app.security;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // 允许跨域的路径
//                        .allowedOrigins("*") // 允许跨域请求的域名
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
//                        .allowedHeaders("*") // 允许的请求头
//                        .allowCredentials(true); // 是否允许证书（cookies）
//            }
//        };
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor myInterceptor = new MyInterceptor();
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").
        excludePathPatterns(
        		"/api/login/*"
        ).
        excludePathPatterns(
        		"/api/books/*"
        )
        ;
        
    }

    private class MyInterceptor implements HandlerInterceptor {
        // 实现你的拦截器逻辑
    }
}
