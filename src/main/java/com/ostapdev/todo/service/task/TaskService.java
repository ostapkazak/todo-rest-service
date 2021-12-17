package com.ostapdev.todo.service.task;

import com.ostapdev.todo.dto.task.TaskDto;

import java.util.List;

public interface TaskService {
    void add(String taskDescription,String username);

    void toggle(Long taskId);

    List<TaskDto> getTasks(Boolean isAll, String target);

    void delete(Long taskId);

    void edit(Long taskId,String taskDescription);
}
