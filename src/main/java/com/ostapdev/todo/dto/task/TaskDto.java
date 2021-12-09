package com.ostapdev.todo.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Long id;
    private String taskDescription;
    private Boolean done;
}
