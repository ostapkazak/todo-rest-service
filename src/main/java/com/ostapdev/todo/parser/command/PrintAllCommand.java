package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class PrintAllCommand extends Command{
    public PrintAllCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.trim().equals(getCommand())) {
            getService().print(true);
        }
    }
}
