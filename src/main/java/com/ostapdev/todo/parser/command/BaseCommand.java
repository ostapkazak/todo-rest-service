package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.service.TodoService;
import lombok.*;

public abstract class BaseCommand implements Command {
    @Getter
    private final TodoDao service = TodoService.getInstance();

    public abstract String getCommand();

    @Override
    public final void run(String inputLine){
        if (isCommand(inputLine,getCommand())) {
            runImpl(inputLine);
        }
    }

    protected abstract void runImpl(String inputLine);

    private boolean isCommand(String inputLine,String command){
        return inputLine.trim().startsWith(command);
    }
}
