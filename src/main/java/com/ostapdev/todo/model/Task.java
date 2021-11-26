package com.ostapdev.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Task {

    private String taskDescription;

    private boolean isDone;
}