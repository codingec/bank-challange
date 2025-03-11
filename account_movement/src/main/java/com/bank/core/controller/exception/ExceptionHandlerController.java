package com.bank.core.controller.exception;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

//@Profile({"production", "test"})
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BadRequest.class)
    public ResponseEntity<ExceptionResponse> handleBadRequest(BadRequest ex) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Bad Request")
                        .message("An error occurred: " + ex.getMessage())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Internal Server Error")
                        .message("An error occurred: " + ex.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
