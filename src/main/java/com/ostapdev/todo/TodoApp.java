package com.ostapdev.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TodoApp {

    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class,args);
    }
}
