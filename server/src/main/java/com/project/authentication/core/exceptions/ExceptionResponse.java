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
    private String message;
    private Boolean userBlocked = false;

    public ExceptionResponse(Date timestamp, String message){
        this.timestamp = timestamp;
        this.message = message;
    };

    public ExceptionResponse(Date timestamp, String message, Boolean userBlocked){
        this.timestamp = timestamp;
        this.message = message;
        this.userBlocked = userBlocked;
    };

}
