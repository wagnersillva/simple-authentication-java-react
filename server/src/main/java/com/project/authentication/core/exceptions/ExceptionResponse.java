package com.project.authentication.core.exceptions;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class ExceptionResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String error;

    public ExceptionResponse(Date timestamp, String message){
        this.timestamp = timestamp;
        this.error = message;
    };

}
