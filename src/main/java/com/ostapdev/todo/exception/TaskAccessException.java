package com.ostapdev.todo.exception;

public class TaskAccessException extends RuntimeException{
    public TaskAccessException(String message) {
        super(message);
    }
}
