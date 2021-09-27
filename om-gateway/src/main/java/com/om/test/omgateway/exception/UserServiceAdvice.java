package com.om.test.omgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserServiceAdvice {

    @ResponseBody
    @ExceptionHandler(UserServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    UserServiceExceptionResponse userNotFoundHandler(UserServiceException exception) {
        UserServiceExceptionResponse response = new UserServiceExceptionResponse("404", exception.getMessage());
        return response;
    }
}
