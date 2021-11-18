package com.ostapdev.todo.parser.command;


import com.ostapdev.todo.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintCommand extends BaseCommand {
    private static String command = "print";

    @Autowired
    public PrintCommand(TodoDao todoDao) {
        super(todoDao);
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        getTodoDao().print(inputLine.replace(command, "").trim().equals("all"));
    }
}
