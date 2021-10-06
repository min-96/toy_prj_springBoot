package org.hdcd.config.security;

import org.hdcd.common.interceptor.AccessLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Interceptor implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLoggingInterceptor()).addPathPatterns("/**").excludePathPatterns("/resource/**");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
    @Bean
    public HandlerInterceptor accessLoggingInterceptor() {
        return new AccessLoggingInterceptor();
    }
}
