package com.alexandria.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiBaseException extends RuntimeException {

  private HttpStatus status;

  public ApiBaseException(String message) {
    super(message);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public StandardError toStandardError() {
    return new StandardError(this);
  }
}
