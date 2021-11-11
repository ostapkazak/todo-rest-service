package com.ostapdev.todo.printer;

public interface ErrorPrinter {
    void printError(String message);
    void printError(String message,Throwable throwable);
}
