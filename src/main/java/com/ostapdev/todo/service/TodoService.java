package com.ostapdev.todo.service;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.model.Task;

import java.util.*;

public class TodoService implements TodoDao {
    private final Map<Integer,Task> tasks = new HashMap<>();
    private Integer lastTaskId = 0;

    @Override
    public void add(String taskDescription) {
        tasks.put(lastTaskId + 1,new Task(taskDescription,false));
        lastTaskId++;
    }

    @Override
    public void toggle(Integer taskId) {
        Task task = tasks.get(taskId);
        try {
            task.setDone(!task.isDone());
            tasks.put(taskId,task);
        }catch (NullPointerException e){
            System.out.println("Задачи с таким идентификатором нет");
        }
    }

    @Override
    public void print(boolean isAll) {
        if (tasks.isEmpty()){
            System.out.println("Список задач пуст");
            return;
        }

        tasks.forEach((id, task) -> {
            if (isAll) printTask(task,id);

            else {
                if (!task.isDone()) printTask(task,id);
            }
        });
    }

    @Override
    public void delete(Integer taskId) {
        Task task = tasks.get(taskId);
        if (task == null){
            System.out.println("Задачи с таким идентификатором нет");
        }

        else {
            tasks.remove(taskId);
        }
    }

    @Override
    public void edit(Integer taskId, String taskDescription) {
        Task task = tasks.get(taskId);
        try {
            task.setTaskDescription(taskDescription);
            tasks.put(taskId,task);
        }catch (NullPointerException e){
            System.out.println("Задачи с таким идентификатором нет");
        }
    }

    @Override
    public void search(String substring) {
        tasks.entrySet().stream()
                .filter(e -> e.getValue().getTaskDescription().contains(substring))
                .forEach(e->printTask(e.getValue(),e.getKey()));
    }

    private void printTask(Task task,Integer taskId){
        System.out.println(taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
    }
}
