package com.ostapdev.todo.parser.command;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.service.TodoService;
import lombok.*;

@RequiredArgsConstructor
public abstract class Command {
    @Getter
    private final TodoDao service = TodoService.getInstance();

    @Getter
    @Setter
    @NonNull
    private String command;

    public abstract void run(String inputLine);
}
