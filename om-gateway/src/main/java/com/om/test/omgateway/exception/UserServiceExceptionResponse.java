package com.om.test.omgateway.exception;

import org.springframework.http.HttpStatus;

public class UserServiceExceptionResponse {
    private HttpStatus httpStatus;
    private String message;

    public UserServiceExceptionResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
