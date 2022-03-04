package com.ostapdev.todo.service.task;

import com.ostapdev.todo.dto.task.TaskDto;

import java.util.List;

public interface TaskService {
    void add(String taskDescription, String username, Boolean remote);

    void toggle(Long taskId, Boolean remote);

    List<TaskDto> getTasks(Boolean isAll, String target, Boolean remote);

    void delete(Long taskId, Boolean remote);

    void edit(Long taskId, String taskDescription, Boolean remote);
}
