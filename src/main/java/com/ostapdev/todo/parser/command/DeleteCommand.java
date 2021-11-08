package com.ostapdev.todo.parser.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeleteCommand extends BaseCommand {
    private static String command  = "delete";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        try {
            getService().delete(Integer.parseInt(inputLine.replace(command,"").trim()));
        }
        catch (NumberFormatException e){
            log.error("Exception: ",e);
            System.out.println("Введенный аргумент не является целым числом");
        }
    }
}
