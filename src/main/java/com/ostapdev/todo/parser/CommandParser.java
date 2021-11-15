package com.ostapdev.todo.parser;

import com.ostapdev.todo.parser.command.BaseCommand;
import com.ostapdev.todo.parser.command.CommandFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CommandParser {
    private CommandParser(){}

    private static CommandParser instance;

    public static CommandParser getInstance(){
        if (instance == null){
            instance = new CommandParser();
        }

        return instance;
    }

    private final List<BaseCommand> commands = CommandFactory.getCommands();

    public void parse(String inputLine){
        log.debug("Input: {}", inputLine);
        for (BaseCommand command : commands){
            command.run(inputLine);
        }
    }
}
