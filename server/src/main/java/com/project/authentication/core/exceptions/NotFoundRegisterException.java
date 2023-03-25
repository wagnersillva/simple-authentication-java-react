package com.project.authentication.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundRegisterException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NotFoundRegisterException(String exception){
        super(exception);
    }
}
