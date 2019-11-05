package com.bamboo.spring.boot.example.config;

import com.bamboo.spring.boot.example.interceptor.ContextInterceptor;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Resource
  private ContextInterceptor contextInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(contextInterceptor).addPathPatterns("/**");
  }
}