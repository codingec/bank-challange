package com.bank.core.controller.exception;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Profile({"production", "test"})
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({Exception.class, RuntimeException.class })
    public ResponseEntity<ExceptionResponse> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .type("Internal Server Error")
                        .message("An error occurred: " + ex.getMessage())
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
