package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.printer.BaseErrorPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteCommand extends BaseCommand {
    private static String command  = "delete";
    private final BaseErrorPrinter baseErrorPrinter;

    @Autowired
    public DeleteCommand(TodoDao todoDao, BaseErrorPrinter baseErrorPrinter) {
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
            getTodoDao().delete(Integer.parseInt(inputLine.replace(command,"").trim()));
        }
        catch (NumberFormatException e){
            baseErrorPrinter.printError("Введенный аргумент не является целым числом",e);
        }
    }
}
