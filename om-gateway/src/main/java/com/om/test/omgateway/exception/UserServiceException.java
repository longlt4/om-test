package com.om.test.omgateway.exception;

import org.springframework.http.HttpStatus;

public class UserServiceException extends RuntimeException {
    public UserServiceException(HttpStatus httpStatus, String message) {
        super(message);
    }
}

