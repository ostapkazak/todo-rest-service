package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.printer.ErrorPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EditCommand extends BaseCommand {
    private static String command = "edit";
    private final ErrorPrinter errorPrinter;

    @Autowired
    public EditCommand(TodoDao todoDao, ErrorPrinter errorPrinter) {
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
            String[] commandArgs = inputLine.replace(command,"").trim().split(" ",2);
            getTodoDao().edit(Integer.parseInt(commandArgs[0]),commandArgs[1]);
        }
        catch (NumberFormatException e){
            errorPrinter.printError("Введенный аргумент не является целым числом",e);
        }
    }
}
