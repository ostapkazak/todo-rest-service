package com.ostapdev.todo.parser.command;


public class PrintCommand extends Command{
    private static String command = "print";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void run(String inputLine) {
        getService().print(inputLine.replace(command, "").trim().equals("all"));
    }
}
