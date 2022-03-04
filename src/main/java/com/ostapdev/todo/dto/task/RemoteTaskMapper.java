package com.ostapdev.todo.dto.task;

import com.ostapdev.todo.remoteTaskService.model.RemoteTask;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface RemoteTaskMapper {
    @BeforeMapping
    default void beforeMapping(@MappingTarget TaskDto target, RemoteTask source){
        if (source.getStatus().equals("ACTIVE")) target.setDone(false);
        else if (source.getStatus().equals("COMPLETED")) target.setDone(true);
    }

    @Mapping(target = "done",ignore = true)
    @Mapping(target = "taskDescription",source = "text")
    List<TaskDto> toListOfDto(List<RemoteTask> remoteTasks);

    @Mapping(target = "done",ignore = true)
    @Mapping(target = "taskDescription",source = "text")
    TaskDto toDto(RemoteTask remoteTask);
}
