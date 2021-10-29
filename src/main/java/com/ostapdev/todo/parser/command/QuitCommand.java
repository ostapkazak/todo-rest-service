package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class QuitCommand extends Command{
    public QuitCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.equals(getCommand())){
            System.exit(0);
        }
    }
}
