package com.bank.core.controller.exception;

import lombok.*;


@Getter
public class BadRequest extends RuntimeException  {
    private String message;

    public BadRequest(){}

    public BadRequest(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }


}
