package com.bamboo.spring.boot.example.exception;

/**
 * ErrorCodeRuntimeException extends RuntimeException, so it can be used in java8 lambda
 */
public class ErrorCodeRuntimeException extends RuntimeException {

  private ErrorCode errorCode;

  public ErrorCodeRuntimeException(ErrorCode errorCode, Object... params) {
    super(errorCode.getFormattedErrorMessage(params));
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

}
