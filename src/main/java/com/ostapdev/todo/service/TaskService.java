package com.ostapdev.todo.service;

import com.ostapdev.todo.model.dto.TaskDto;
import com.ostapdev.todo.model.Task;

import java.util.List;

public interface TaskService {
    void add(TaskDto task);

    void toggle(Long taskId);

    List<Task> getTasks(Boolean isAll, String target);

    void delete(Long taskId);

    void edit(Long taskId,String taskDescription);
}
