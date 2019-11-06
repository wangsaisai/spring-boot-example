package com.bamboo.spring.boot.example.exception;

import java.text.MessageFormat;
import org.springframework.http.HttpStatus;

public enum ErrorCode {

  // 400
  MISS_ATTRIBUTE(HttpStatus.BAD_REQUEST, "400-00-1", "missing attributes : {0}"),

  DEMO_NAME_EXIST(HttpStatus.BAD_REQUEST, "400-02-1", "Demo name : {0} exist"),
  DEMO_TYPE_UNSUPPORT(HttpStatus.BAD_REQUEST, "400-02-2", "Demo type : {0} unSupport"),

  // 404
  DEMO_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "404-01-1", "Demo id : {0} not found"),
  DEMO_NAME_NOT_FOUND(HttpStatus.NOT_FOUND, "404-01-2", "Demo name : {0} not found"),

  // 500
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500-00-1", "Unexpected Error"),

  REST_TEMPLATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500-03-1", "rest template error"),
  REST_TEMPLATE_CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500-03-2",
      "rest template client error : \n{0}\n"),
  REST_TEMPLATE_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500-03-3",
      "rest template server error : \n{0}\n");

  private static final String SERVICE_NAME = "spring-boot-example";

  private String errorCode;
  private String errorMessage;
  private HttpStatus httpStatus;


  ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
    this.httpStatus = httpStatus;
    this.errorCode = SERVICE_NAME + errorCode;
    this.errorMessage = errorMessage;
  }

  public String getFormattedErrorMessage(Object... params) {
    return new MessageFormat(errorMessage).format(params);
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public String getErrorCode() {
    return errorCode;
  }

}
