package com.ostapdev.todo.dao;

import com.ostapdev.todo.dto.TaskDto;
import com.ostapdev.todo.model.Task;

import java.util.Map;

public interface TaskDao {
    void add(TaskDto task);

    void toggle(Integer taskId);

    Map<Integer,Task> getTasks(Boolean isAll,String target);

    void delete(Integer taskId);

    void edit(Integer taskId,String taskDescription);
}
