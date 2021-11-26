package com.ostapdev.todo.service;

import com.ostapdev.todo.dao.TaskDao;
import com.ostapdev.todo.dto.TaskDto;
import com.ostapdev.todo.exception.EmptyListException;
import com.ostapdev.todo.exception.NoSuchTaskException;
import com.ostapdev.todo.model.Task;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TaskService implements TaskDao {
    private final Map<Integer,Task> tasks = new HashMap<>();
    private Integer lastTaskId = 0;


    @Override
    public void add(TaskDto task) {
        tasks.put(lastTaskId + 1,new Task(task.getTaskDescription(),false));
        lastTaskId++;
    }

    @Override
    public void toggle(Integer taskId) {
        Task task = tasks.get(taskId);

        if (task == null){
            throw new NoSuchTaskException("Задачи с id " + taskId + " нет");
        }

        else {
            task.setDone(!task.isDone());
            tasks.put(taskId,task);
        }
    }

    @Override
    public Map<Integer,Task> getTasks(Boolean isAll,String target) {
        if (tasks.isEmpty()){
            throw new EmptyListException("Список задач пуст");
        }

        if (target == null && isAll == null){
            return tasks;
        }

        final Map<Integer,Task> tasksWithTarget = new HashMap<>(Collections.emptyMap());

        tasks.entrySet().stream()
                .filter(e -> {
                    if (target != null) {
                        return e.getValue().getTaskDescription().contains(target);
                    }

                    return true;
                })
                .forEach(e->{
                    if (isAll != null){
                        if (isAll) tasksWithTarget.put(e.getKey(),e.getValue());

                        else{
                            if (!e.getValue().isDone()) tasksWithTarget.put(e.getKey(),e.getValue());
                        }
                    }

                    else {
                        if (!e.getValue().isDone()) tasksWithTarget.put(e.getKey(),e.getValue());
                    }
                });

        return tasksWithTarget;
    }

    @Override
    public void delete(Integer taskId) {
        Task task = tasks.get(taskId);
        if (task == null){
            throw new NoSuchTaskException("Задачи с id " + taskId + " нет");
        }

        else {
            tasks.remove(taskId);
        }
    }

    @Override
    public void edit(Integer taskId, String taskDescription) {
        Task task = tasks.get(taskId);

        if (task == null){
            throw new NoSuchTaskException("Задачи с id " + taskId + " нет");
        }

        else {
            task.setTaskDescription(taskDescription);
            tasks.put(taskId,task);
        }
    }
}
