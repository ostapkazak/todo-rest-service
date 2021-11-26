package com.ostapdev.todo.exception;

public class NoSuchTaskException extends RuntimeException{
    public NoSuchTaskException(String message) {
        super(message);
    }
}
