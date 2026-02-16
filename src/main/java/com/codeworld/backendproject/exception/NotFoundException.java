package com.codeworld.backendproject.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(String entityName, Object id) {
        super(entityName + "tapılmadı. id " + id);
    }
}
