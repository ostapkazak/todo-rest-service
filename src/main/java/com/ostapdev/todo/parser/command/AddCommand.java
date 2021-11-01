package com.ostapdev.todo.parser.command;


public class AddCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "add";

        if (isCommand(inputLine,command)) {
            getService().add(inputLine.replace(command,"").trim());
        }
    }
}
