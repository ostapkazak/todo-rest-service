package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.service.TodoService;
import lombok.*;

public abstract class Command implements CommandInterface{
    @Getter
    private final TodoDao service = TodoService.getInstance();

    @Override
    public abstract void run(String inputLine);

    public boolean isCommand(String inputLine,String command){
        return inputLine.trim().startsWith(command);
    }
}
