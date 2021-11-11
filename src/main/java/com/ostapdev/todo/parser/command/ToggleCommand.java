package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.printer.BaseErrorPrinter;
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
            BaseErrorPrinter.getInstance().printError("Введенный аргумент не является целым числом",e);
        }
    }
}
