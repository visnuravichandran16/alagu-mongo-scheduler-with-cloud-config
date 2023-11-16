package com.dynamic.taskmongo.exception;

public class InvalidCollectionException extends RuntimeException{

    public InvalidCollectionException(String message) {
        super(message);
    }
}
