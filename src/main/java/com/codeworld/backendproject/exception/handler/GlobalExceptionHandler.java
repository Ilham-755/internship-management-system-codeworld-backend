package com.codeworld.backendproject.exception.handler;

import com.codeworld.backendproject.exception.DuplicateApplicationException;
import com.codeworld.backendproject.exception.NoActiveProgramException;
import com.codeworld.backendproject.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoActiveProgramException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleNoActiveProgramException(NoActiveProgramException ex) {
//        return ex.getMessage();
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DuplicateApplicationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiErrorResponse handlebyDup(DuplicateApplicationException ex) {
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound (NotFoundException ex){
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    private ApiErrorResponse build(HttpStatus status, String message) {
        ApiErrorResponse a = new ApiErrorResponse();
        a.setTimestamp(LocalDateTime.now());
        a.setStatus(status.value());
        a.setError(status.name());
        a.setMessage(message);

        return a;
    }
}
