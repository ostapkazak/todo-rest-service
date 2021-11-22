package com.ostapdev.todo;

import com.ostapdev.todo.parser.CommandParser;
import com.ostapdev.todo.printer.ErrorPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@SpringBootApplication
public class TodoApp implements CommandLineRunner {
    private final CommandParser commandParser;
    private final ErrorPrinter errorPrinter;
    private final BufferedReader reader;

    @Autowired
    public TodoApp(CommandParser commandParser, ErrorPrinter errorPrinter, BufferedReader reader) {
        this.commandParser = commandParser;
        this.errorPrinter = errorPrinter;
        this.reader = reader;
    }

    public static void main(String[] args) {
        SpringApplication.run(TodoApp.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true){
            try {
                commandParser.parse(reader.readLine());
            }catch (IOException e){
                errorPrinter.printError("IO",e);
            }
        }
    }
}
