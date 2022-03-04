package com.ostapdev.todo.remoteTaskService.model;

import lombok.Getter;

@Getter
public class RemoteTask {
    private Long id;
    private String text;
    private String status;
}
