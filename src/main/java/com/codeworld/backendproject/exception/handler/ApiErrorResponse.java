package com.codeworld.backendproject.exception.handler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiErrorResponse {
    private String message;
    private String error;
    private int status;
    private LocalDateTime timestamp;
}
