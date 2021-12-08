package com.ostapdev.todo.dto.mapper;

import com.ostapdev.todo.dto.TaskDto;
import com.ostapdev.todo.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper  {
    TaskMapper instance = Mappers.getMapper(TaskMapper.class);

    List<TaskDto> toListOfDto(List<Task> tasks);

    TaskDto toDto(Task task);
}
