package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand extends BaseCommand {
    private static String command = "quit";

    @Autowired
    public QuitCommand(TodoDao todoDao) {
        super(todoDao);
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    protected void runImpl(String inputLine) {
        System.exit(0);
    }
}
