package com.ostapdev.todo.exception;

public class NoSuchDataException extends RuntimeException{
    public NoSuchDataException(String message) {
        super(message);
    }
}
