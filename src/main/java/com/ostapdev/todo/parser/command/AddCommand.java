package com.ostapdev.todo.parser.command;


import com.ostapdev.todo.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddCommand extends BaseCommand {
    private static String command = "add";

    @Autowired
    public AddCommand(TodoDao todoDao) {
        super(todoDao);
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        getTodoDao().add(inputLine.replace(command,"").trim());
    }
}
