package com.ostapdev.todo.parser.command;


public class PrintCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "print";

        if (isCommand(inputLine,command)){
            getService().print(inputLine.replace(command, "").trim().equals("all"));
        };
    }
}
