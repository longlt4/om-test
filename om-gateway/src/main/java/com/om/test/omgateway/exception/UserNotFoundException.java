package com.om.test.omgateway.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not find user " + id);
    }
}

