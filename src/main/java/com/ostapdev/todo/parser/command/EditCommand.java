package com.ostapdev.todo.parser.command;

import lombok.NonNull;

public class EditCommand extends Command{
    public EditCommand(@NonNull String command) {
        super(command);
    }

    @Override
    public void run(String inputLine) {
        if (inputLine.startsWith(getCommand())){
            try {
                String[] commandArgs = inputLine.replace(getCommand(),"").trim().split(" ",2);
                getService().edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
            }
            catch (NumberFormatException e){
                System.out.println("Введенный аргумент не является целым числом");
            }
        }
    }
}
