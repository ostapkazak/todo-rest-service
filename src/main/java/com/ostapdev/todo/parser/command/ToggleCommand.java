package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class ToggleCommand extends Command{
    public ToggleCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.startsWith(getCommand())){
            try {
                getService().toggle(Integer.parseInt(inputLine.replace(getCommand(),"").trim()));
            }
            catch (NumberFormatException e){
                System.out.println("Введенный аргумент не является целым числом");
            }
        }
    }
}
