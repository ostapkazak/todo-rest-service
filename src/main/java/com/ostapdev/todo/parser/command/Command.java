package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.service.TodoService;
import lombok.*;

public abstract class Command implements CommandInterface{
    @Getter
    private final TodoDao service = TodoService.getInstance();

    public abstract String getCommand();

    @Override
    public void checkInput(String inputLine){
        if (isCommand(inputLine,getCommand())) {
            this.run(inputLine);
        }
    }

    protected abstract void run(String inputLine);

    private boolean isCommand(String inputLine,String command){
        return inputLine.trim().startsWith(command);
    }
}
