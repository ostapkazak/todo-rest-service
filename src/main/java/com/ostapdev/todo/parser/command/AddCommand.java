package com.ostapdev.todo.parser.command;


public class AddCommand extends BaseCommand {
    private static String command = "add";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        getService().add(inputLine.replace(command,"").trim());
    }
}
