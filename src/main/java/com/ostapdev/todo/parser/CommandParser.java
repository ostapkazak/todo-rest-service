package com.ostapdev.todo.parser;

import com.ostapdev.todo.parser.command.BaseCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CommandParser {
    private final List<BaseCommand> commands;

    @Autowired
    public CommandParser(List<BaseCommand> commands) {
        this.commands = commands;
    }

    public void parse(String inputLine){
        log.debug("Input: {}", inputLine);
        for (BaseCommand command : commands){
            command.run(inputLine);
        }
    }
}
