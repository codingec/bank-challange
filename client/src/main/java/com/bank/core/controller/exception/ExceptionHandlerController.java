package com.bank.core.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException ex, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .type("Bad Request")
                        .message("Null Pointer Exception: " + ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type("Internal Server Error")
                        .message("An error occurred: " + ex.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
