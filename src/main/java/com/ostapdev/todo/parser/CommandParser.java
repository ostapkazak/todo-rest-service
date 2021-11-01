package com.ostapdev.todo.parser;

import com.ostapdev.todo.parser.command.Command;
import com.ostapdev.todo.parser.command.CommandFactory;

import java.util.List;

public class CommandParser {
    private final List<Command> commands = CommandFactory.getCommands();

    public void parse(String inputLine){
        for (Command command : commands){
            command.checkInput(inputLine);
        }
    }
}
