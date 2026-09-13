package org.project.librarymanagementapi.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorResponse {
    private boolean success = false;
    private String message;
    private int statusCode;
    private LocalDateTime timestamp = LocalDateTime.now();
    private Map<String, String> errors;

    public ErrorResponse(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode.value();
    }

    public ErrorResponse(String message, HttpStatus statusCode, Map<String, String> errors) {
        this.message = message;
        this.statusCode = statusCode.value();
        this.errors = errors;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
