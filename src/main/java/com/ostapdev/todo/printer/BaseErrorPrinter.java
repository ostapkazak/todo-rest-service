package com.ostapdev.todo.printer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseErrorPrinter implements ErrorPrinter{

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
