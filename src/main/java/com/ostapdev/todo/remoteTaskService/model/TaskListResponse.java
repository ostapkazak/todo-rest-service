package com.ostapdev.todo.remoteTaskService.model;

import lombok.Getter;

import java.util.List;

@Getter
public class TaskListResponse extends RemoteTaskServiceResponse{
    private List<RemoteTask> data;
}
