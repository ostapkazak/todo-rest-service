package com.ostapdev.todo.remoteTaskService.model;

import lombok.Getter;

@Getter
public class TaskResponse extends RemoteTaskServiceResponse{
    private RemoteTask data;
}
