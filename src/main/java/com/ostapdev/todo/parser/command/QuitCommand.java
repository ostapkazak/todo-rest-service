package com.ostapdev.todo.parser.command;


public class QuitCommand extends BaseCommand {
    private static String command = "quit";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        System.exit(0);
    }
}
