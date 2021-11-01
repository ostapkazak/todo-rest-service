package com.ostapdev.todo.parser.command;


public class QuitCommand extends Command{
    private static String command = "quit";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void run(String inputLine) {
        System.exit(0);
    }
}
