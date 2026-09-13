package org.project.librarymanagementapi.dto;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private boolean success = true;
    private String message;
    private int httpStatus;
    private T data;

    public ApiResponse(
            String message
    ) {
        this.message = message;
        this.httpStatus = HttpStatus.OK.value();
    }

    public ApiResponse(
            String message,
            T data
    ) {
       this.message = message;
       this.data = data;
       this.httpStatus = HttpStatus.OK.value();
    }

    public ApiResponse(
            String message,
            T data,
            HttpStatus httpStatus
    ) {
        this.message = message;
        this.httpStatus = httpStatus.value();
        this.data = data;
    }

    public ApiResponse(
            String message,
            T data,
            int httpStatus
    ) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public T getData() {
        return data;
    }
}
