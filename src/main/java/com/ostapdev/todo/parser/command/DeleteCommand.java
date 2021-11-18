package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.printer.ErrorPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteCommand extends BaseCommand {
    private static String command  = "delete";
    private final ErrorPrinter errorPrinter;

    @Autowired
    public DeleteCommand(TodoDao todoDao, ErrorPrinter errorPrinter) {
        super(todoDao);
        this.errorPrinter = errorPrinter;
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
            errorPrinter.printError("Введенный аргумент не является целым числом",e);
        }
    }
}
