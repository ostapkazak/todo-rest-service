package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Task;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface CustomTaskRepo {
    @Async
    CompletableFuture<List<Task>> find(String targetDescription,Boolean includeCompleted,Long accountId);
}
