package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Task;

import java.util.List;

public interface CustomTaskRepo {
    List<Task> find(String targetDescription,Boolean includeCompleted,Long accountId);
}
