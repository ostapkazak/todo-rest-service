package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.printer.BaseErrorPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EditCommand extends BaseCommand {
    private static String command = "edit";
    private final BaseErrorPrinter baseErrorPrinter;

    @Autowired
    public EditCommand(TodoDao todoDao, BaseErrorPrinter baseErrorPrinter) {
        super(todoDao);
        this.baseErrorPrinter = baseErrorPrinter;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        try {
            String[] commandArgs = inputLine.replace(command,"").trim().split(" ",2);
            getTodoDao().edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
        }
        catch (NumberFormatException e){
            baseErrorPrinter.printError("Введенный аргумент не является целым числом",e);
        }
    }
}
