package com.ostapdev.todo.parser.command;


public class SearchCommand extends Command{
    private static String command = "search";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void run(String inputLine) {
        getService().search(inputLine.replace(command,"").trim());
    }
}
