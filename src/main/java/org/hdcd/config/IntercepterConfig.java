package org.hdcd.config;

import org.hdcd.common.AccessLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//사용자가 시스템 접근한 정보를 기록하기위해 접근로깅처리
@Configuration
public class IntercepterConfig implements WebMvcConfigurer {


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
