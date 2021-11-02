package com.ostapdev.todo.parser.command;


public class SearchCommand extends BaseCommand {
    private static String command = "search";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        getService().search(inputLine.replace(command,"").trim());
    }
}
