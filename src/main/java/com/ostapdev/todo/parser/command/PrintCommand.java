package com.ostapdev.todo.parser.command;


public class PrintCommand extends BaseCommand {
    private static String command = "print";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        getService().print(inputLine.replace(command, "").trim().equals("all"));
    }
}
