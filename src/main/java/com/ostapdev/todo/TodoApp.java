package com.ostapdev.todo;

import com.ostapdev.todo.parser.CommandParser;
import com.ostapdev.todo.printer.BaseErrorPrinter;
import com.ostapdev.todo.printer.ErrorPrinter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TodoApp {
    public static void main(String[] args) {
        CommandParser commandParser = CommandParser.getInstance();
        ErrorPrinter errorPrinter = BaseErrorPrinter.getInstance();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                commandParser.parse(reader.readLine());
            }catch (IOException e){
                errorPrinter.printError("IO",e);
            }
        }
    }
}
