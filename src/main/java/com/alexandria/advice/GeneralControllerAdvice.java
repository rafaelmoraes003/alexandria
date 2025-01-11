package com.alexandria.advice;

import com.alexandria.exception.ApiBaseException;
import com.alexandria.exception.StandardError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<StandardError> errorHandler(Exception e) {
    StandardError error;

    if (e instanceof ApiBaseException) {
      error = ((ApiBaseException) e).toStandardError();
    } else {
      error = new StandardError(e.getMessage());
    }

    return ResponseEntity.status(error.getStatus()).body(error);
  }

}
