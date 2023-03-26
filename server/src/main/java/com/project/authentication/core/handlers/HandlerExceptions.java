package com.project.authentication.core.handlers;

import com.project.authentication.core.exceptions.ExceptionResponse;
import com.project.authentication.core.exceptions.NotFoundRegisterException;
import com.project.authentication.core.exceptions.UserBlockedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class HandlerExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class, NotFoundRegisterException.class, IllegalArgumentException.class })
    public final ResponseEntity<Object> handleException(Exception ex){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if(ex instanceof NotFoundRegisterException){
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (ex instanceof IllegalArgumentException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return responseEntity(ex, httpStatus);
    }

    @ExceptionHandler({UserBlockedException.class})
    public final ResponseEntity<Object> handleUserBlockedException(Exception ex){
        return responseEntity(ex, HttpStatus.UNAUTHORIZED, true);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        String error = String.join(", ", errors);
        return responseEntity(error, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> responseEntity(Exception ex, HttpStatus httpStatus){
        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(new Date(), ex.getMessage()));
    }

    private ResponseEntity<Object> responseEntity(Exception ex, HttpStatus httpStatus, Boolean userBlocked){
        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(new Date(), ex.getMessage(), userBlocked));
    }

    private ResponseEntity<Object> responseEntity(String message, HttpStatus httpStatus){
        return ResponseEntity.status(httpStatus).body(new ExceptionResponse(new Date(), message));
    }

}
