package com.ostapdev.todo.dto.task;

import com.ostapdev.todo.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TaskMapper  {
    @Mapping(target = "isRemote",constant = "false")
    List<TaskDto> toListOfDto(List<Task> tasks);

    @Mapping(target = "isRemote",constant = "false")
    TaskDto toDto(Task task);
}
