package com.ostapdev.todo.parser.command;


public class SearchCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "search";

        if (isCommand(inputLine,command)) getService().search(inputLine.replace(command,"").trim());
    }
}
