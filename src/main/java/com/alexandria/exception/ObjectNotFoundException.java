package com.alexandria.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends ApiBaseException {

  public ObjectNotFoundException(String message) {
    super(message);
    setStatus(HttpStatus.NOT_FOUND);
  }
}
