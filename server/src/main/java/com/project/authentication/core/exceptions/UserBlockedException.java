package com.project.authentication.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserBlockedException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserBlockedException(String exception){
        super(exception);
    }
}