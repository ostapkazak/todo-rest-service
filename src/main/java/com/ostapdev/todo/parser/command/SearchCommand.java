package com.ostapdev.todo.parser.command;


import com.ostapdev.todo.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchCommand extends BaseCommand {
    private static String command = "search";

    @Autowired
    public SearchCommand(TodoDao todoDao) {
        super(todoDao);
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        getTodoDao().search(inputLine.replace(command,"").trim());
    }
}
