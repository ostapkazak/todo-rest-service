package com.ostapdev.todo.parser;

import com.ostapdev.todo.parser.command.BaseCommand;
import com.ostapdev.todo.parser.command.CommandFactory;

import java.util.List;

public class CommandParser {
    private final List<BaseCommand> commands = CommandFactory.getCommands();

    public void parse(String inputLine){
        for (BaseCommand command : commands){
            command.run(inputLine);
        }
    }
}
