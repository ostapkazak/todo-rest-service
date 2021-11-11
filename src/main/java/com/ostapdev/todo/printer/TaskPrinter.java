package com.ostapdev.todo.printer;

import com.ostapdev.todo.model.Task;

public interface TaskPrinter {
    void printTask(Task task, Integer taskId);
}
