package com.ostapdev.todo.service;

import com.ostapdev.todo.dto.TaskDto;
import com.ostapdev.todo.dto.mapper.TaskMapper;
import com.ostapdev.todo.exception.NoSuchTaskException;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo repo;
    private final TaskMapper mapper = TaskMapper.instance;

    @Override
    public void add(String taskDescription) {
        repo.save(new Task(taskDescription,false));
    }

    @Override
    public void toggle(Long taskId) {
        Task task = repo.findById(taskId).orElseThrow(()->new NoSuchTaskException("Задачи с id " + taskId + " нет"));
        task.setDone(!task.getDone());
        repo.save(task);
    }

    @Override
    public List<TaskDto> getTasks(Boolean isAll, String target) {
        return mapper.toListOfDto(repo.find(target,isAll));
    }

    @Override
    public void delete(Long taskId) {
        Task task = repo.findById(taskId).orElseThrow(()->new NoSuchTaskException("Задачи с id " + taskId + " нет"));
        repo.delete(task);
    }

    @Override
    public void edit(Long taskId, String taskDescription) {
        Task task = repo.findById(taskId).orElseThrow(()->new NoSuchTaskException("Задачи с id " + taskId + " нет"));
        task.setTaskDescription(taskDescription);
        repo.save(task);
    }
}
