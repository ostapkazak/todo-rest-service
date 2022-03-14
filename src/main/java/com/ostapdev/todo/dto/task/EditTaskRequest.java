package com.ostapdev.todo.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class EditTaskRequest {
    @NotBlank(message = "Task description may not be blank")
    private String description;
}
