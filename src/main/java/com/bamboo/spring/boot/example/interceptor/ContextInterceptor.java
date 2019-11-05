package com.bamboo.spring.boot.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class ContextInterceptor extends HandlerInterceptorAdapter {

  private final Logger LOGGER = LoggerFactory.getLogger(ContextInterceptor.class);

  private static String getRemoteIP(HttpServletRequest request) {
    String address;
    return (address = request.getHeader("x-forwarded-for")) == null ? request.getRemoteAddr()
        : address;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {
    int responseCode = response.getStatus();
    if ("/health".equals(request.getRequestURI())) {
      if (responseCode >= 400) {
        LOGGER.error("Web Service is not health, /health api responseCode: {}", responseCode);
      } else if (LOGGER.isDebugEnabled()) {
        LOGGER.debug(
            "Context Success Request: [ url: {}, method: {}, responseCode: {}, remoteAddress: {} ]",
            request.getRequestURI(),
            request.getMethod(),
            responseCode,
            getRemoteIP(request));
      }
      return;
    }

    if (responseCode >= 400) {
      LOGGER.error(
          "Context Error Request: [ url: {}, method: {}, responseCode: {}, remoteAddress: {} ]",
          request.getRequestURI(),
          request.getMethod(),
          responseCode,
          getRemoteIP(request));
    } else {
      LOGGER.info(
          "Context Success Request: [ url: {}, method: {}, responseCode: {}, remoteAddress: {} ]",
          request.getRequestURI(),
          request.getMethod(),
          responseCode,
          getRemoteIP(request));
    }
  }

}