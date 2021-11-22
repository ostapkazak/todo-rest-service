package com.ostapdev.todo.printer;

import com.ostapdev.todo.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseTaskPrinter implements TaskPrinter {

    @Override
    public void printTask(Task task, Integer taskId) {
        log.debug("out: " + taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
        System.out.println(taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
    }
}
