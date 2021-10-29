package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class AddCommand extends Command{

    public AddCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.startsWith(getCommand())){
            getService().add(inputLine.replace(getCommand(),"").trim());
        }
    }
}
