package com.bamboo.spring.boot.example.interceptor;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateInterceptor.class);

  /**
   * Log error response from other rest services.
   */
  @Override
  @NonNull
  public ClientHttpResponse intercept(@NonNull HttpRequest request, @NonNull byte[] body,
      ClientHttpRequestExecution execution)
      throws IOException {
    ClientHttpResponse response = execution.execute(request, body);

    int responseCode = response.getRawStatusCode();
    if (responseCode >= 400) {
      LOGGER.error("RestTemplate Error request: [ url: {}, method: {}, responseCode: {} ]",
          request.getURI(), request.getMethod(), responseCode);
    } else {
      LOGGER.info("RestTemplate Success Request: [ url: {}, method: {}, responseCode: {} ]",
          request.getURI(), request.getMethod(), responseCode);
    }

    return response;
  }
}