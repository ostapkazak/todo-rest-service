package com.ostapdev.todo.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String taskDescription;
    private Boolean done;
}
