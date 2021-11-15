package com.ostapdev.todo.printer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseErrorPrinter implements ErrorPrinter{
    private BaseErrorPrinter(){}

    private static BaseErrorPrinter instance;

    public static BaseErrorPrinter getInstance(){
        if (instance == null){
            instance = new BaseErrorPrinter();
        }

        return instance;
    }

    @Override
    public void printError(String message) {
        log.error(message);
        System.out.println(message);
    }

    @Override
    public void printError(String message, Throwable throwable) {
        log.error("Exception: ",throwable);
        System.out.println(message);
    }
}
