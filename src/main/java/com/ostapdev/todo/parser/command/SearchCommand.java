package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class SearchCommand extends Command{
    public SearchCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.startsWith(getCommand())){
            getService().search(inputLine.replace(getCommand(),"").trim());
        }
    }
}
