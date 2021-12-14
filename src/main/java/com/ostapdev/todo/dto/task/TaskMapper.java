package com.ostapdev.todo.dto.task;

import com.ostapdev.todo.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper  {
    List<TaskDto> toListOfDto(List<Task> tasks);

    TaskDto toDto(Task task);
}
