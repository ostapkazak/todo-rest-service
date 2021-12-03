package com.ostapdev.todo.service;

import com.ostapdev.todo.model.dto.TaskDto;
import com.ostapdev.todo.exception.NoSuchTaskException;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo repo;

    @Autowired
    public TaskServiceImpl(TaskRepo repo) {
        this.repo = repo;
    }


    @Override
    public void add(TaskDto task) {
        repo.save(new Task(task.getTaskDescription(),false));
    }

    @Override
    public void toggle(Long taskId) {
        Task task = repo.findById(taskId).orElseThrow(()->new NoSuchTaskException("Задачи с id " + taskId + " нет"));
        task.setDone(!task.getDone());
        repo.save(task);
    }

    @Override
    public List<Task> getTasks(Boolean isAll,String target) {
        if (target == null && isAll == null){
            return repo.findAllByDone(false);
        }

        if (isAll != null){
            if (target == null) {
                if (isAll) return repo.findAll();
                else return repo.findAllByDone(false);
            }
            else return repo.findAllByDoneAndTaskDescriptionIsLike(isAll,"%"+target+"%");
        }

        else {
            return repo.findAllByTaskDescriptionIsLike("%"+target+"%");
        }
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
