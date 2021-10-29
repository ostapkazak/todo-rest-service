package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class PrintCommand extends Command{

    public PrintCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.trim().equals(getCommand())) {
            getService().print(false);
        }
    }
}
