package com.ostapdev.todo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "Task description may not be null")
    @NotBlank(message = "Task description may not be blank")
    @NotEmpty(message = "Task description may not be empty")
    private String taskDescription;
}
