package com.dynamic.taskmongo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@PropertySource("classpath:/error.properties")
public class GlobalExceptionHandler {

    private Environment env;

    @Autowired
    public GlobalExceptionHandler(Environment env) {
        this.env = env;
    }

    @ExceptionHandler(InvalidCollectionException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleInvalidCollection(InvalidCollectionException e) {
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND,env.getProperty(e.getMessage())),HttpStatus.NOT_FOUND);
    }
}
