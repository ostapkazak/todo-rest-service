package com.ostapdev.todo;

import com.ostapdev.todo.parser.CommandParser;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TodoApp {
    public static void main(String[] args) {
        CommandParser commandParser = new CommandParser();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try {
                commandParser.parse(reader.readLine());
            }catch (IOException e){
                log.error(e.toString());
                System.out.println("IO");
            }
        }
    }
}
