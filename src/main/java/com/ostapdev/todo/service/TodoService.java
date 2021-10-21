package com.ostapdev.todo.service;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.model.Task;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TodoService implements TodoDao {
    private final Map<Integer,Task> tasks = new LinkedHashMap<>();

    @Override
    public void add(String taskDescription) {
        if (tasks.isEmpty()) {
            tasks.put(1,new Task(taskDescription,false));
            return;
        }

        List<Integer> keys = new ArrayList<>(tasks.keySet());
        tasks.put(keys.get(keys.size()-1)+1,new Task(taskDescription,false));
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

        tasks.forEach((id, task) -> printTask(isAll,task,id));
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
        tasks.forEach((id,task)->{
            if (task.getTaskDescription().contains(substring)) printTask(true,task,id);
        });
    }

    private void printTask(boolean isAllMark,Task task,Integer taskId){
        if (isAllMark) System.out.println(taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());

        else {
            if (!task.isDone()) {
                System.out.println(taskId + ". " + task.getTaskDescription());
            }
        }
    }
}
