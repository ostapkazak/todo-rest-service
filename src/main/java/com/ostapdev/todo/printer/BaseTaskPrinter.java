package com.ostapdev.todo.printer;

import com.ostapdev.todo.model.Task;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseTaskPrinter implements TaskPrinter {
    private BaseTaskPrinter(){}

    private static BaseTaskPrinter instance;

    public static BaseTaskPrinter getInstance(){
        if (instance == null){
            instance = new BaseTaskPrinter();
        }

        return instance;
    }

    @Override
    public void printTask(Task task, Integer taskId) {
        log.debug("out: " + taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
        System.out.println(taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
    }
}
