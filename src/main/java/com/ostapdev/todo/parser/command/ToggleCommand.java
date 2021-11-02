package com.ostapdev.todo.parser.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToggleCommand extends BaseCommand {
    private static String command = "toggle";

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void runImpl(String inputLine) {
        try {
            getService().toggle(Integer.parseInt(inputLine.replace(command,"").trim()));
        }
        catch (NumberFormatException e){
            log.error(e.toString());
            System.out.println("Введенный аргумент не является целым числом");
        }
    }
}
