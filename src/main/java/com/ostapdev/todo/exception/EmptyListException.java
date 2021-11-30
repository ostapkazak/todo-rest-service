package com.ostapdev.todo.exception;

public class EmptyListException extends RuntimeException{
    public EmptyListException(String message) {
        super(message);
    }
}
