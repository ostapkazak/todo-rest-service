package com.ostapdev.todo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class TaskDto {
    @NotBlank(message = "Task description may not be blank")
    private String taskDescription;
}
