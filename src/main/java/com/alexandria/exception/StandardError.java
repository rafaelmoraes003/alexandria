package com.alexandria.exception;

import java.time.Instant;
import org.springframework.http.HttpStatus;

public class StandardError {

  private String message;
  private HttpStatus status;
  private final Instant timestamp = Instant.now();

  public StandardError(String message) {
    this.message = message;
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public StandardError(ApiBaseException e) {
    this.message = e.getMessage();
    this.status = e.getStatus();
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
