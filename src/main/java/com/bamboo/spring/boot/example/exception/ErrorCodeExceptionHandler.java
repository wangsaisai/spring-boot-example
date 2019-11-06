package com.bamboo.spring.boot.example.exception;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
@ResponseBody
public class ErrorCodeExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodeExceptionHandler.class);


  @ExceptionHandler(value = ErrorCodeRuntimeException.class)
  public ResponseEntity<Map<String, Object>> handleErrorCodeRuntimeException(
      ErrorCodeRuntimeException e, HttpServletRequest request) {
    ErrorCode errorCode = e.getErrorCode();
    LOGGER.error("errorCode: " + errorCode.getErrorCode(), e);

    return setExceptionResponse(request, errorCode, e.getMessage());
  }

  @ExceptionHandler(value = RestClientException.class)
  public ResponseEntity<Map<String, Object>> handleRestClientException(
      RestClientException e, HttpServletRequest request) {
    LOGGER.error("Exception when call other rest service ", e);

    return setExceptionResponse(request, ErrorCode.REST_TEMPLATE_ERROR, e.getMessage());
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Map<String, Object>> handleUnExpectedException(Exception e,
      HttpServletRequest request) {
    LOGGER.error("UnexpectedException", e);

    return setExceptionResponse(request, ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
  }


  private ResponseEntity<Map<String, Object>> setExceptionResponse(HttpServletRequest request,
      ErrorCode errorCode, String message) {
    request
        .setAttribute("javax.servlet.error.status_code", errorCode.getHttpStatus().value());
    request.setAttribute("javax.servlet.error.message", message);
    request.setAttribute("javax.servlet.error.error", errorCode.getHttpStatus().toString());
    request.setAttribute("javax.servlet.error.error_code", errorCode.getErrorCode());
    request.setAttribute("javax.servlet.error.request_uri", request.getRequestURI());
    Map<String, Object> map = (new DefaultErrorAttributes())
        .getErrorAttributes(new ServletWebRequest(request), false);
    return new ResponseEntity<>(map, errorCode.getHttpStatus());
  }

}
