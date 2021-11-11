package com.ostapdev.todo.parser.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EditCommand extends BaseCommand {
    private static String command = "edit";
    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        try {
            String[] commandArgs = inputLine.replace(command,"").trim().split(" ",2);
            getService().edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
        }
        catch (NumberFormatException e){
            getErrorPrinter().printError("Введенный аргумент не является целым числом",e);
        }
    }
}
