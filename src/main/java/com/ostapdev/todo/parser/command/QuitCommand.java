package com.ostapdev.todo.parser.command;


public class QuitCommand extends Command{
    @Override
    public void run(String inputLine) {
        final String command = "quit";

        if (isCommand(inputLine,command)) System.exit(0);
    }
}
