package com.ostapdev.todo.parser.command;


public class AddCommand extends Command {
    private static String command = "add";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void run(String inputLine) {
        getService().add(inputLine.replace(command,"").trim());
    }
}
