package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.printer.BaseErrorPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ToggleCommand extends BaseCommand {
    private static String command = "toggle";
    private final BaseErrorPrinter baseErrorPrinter;

    @Autowired
    public ToggleCommand(TodoDao todoDao, BaseErrorPrinter baseErrorPrinter) {
        super(todoDao);
        this.baseErrorPrinter = baseErrorPrinter;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public void runImpl(String inputLine) {
        try {
            getTodoDao().toggle(Integer.parseInt(inputLine.replace(command,"").trim()));
        }
        catch (NumberFormatException e){
            baseErrorPrinter.printError("Введенный аргумент не является целым числом",e);
        }
    }
}
