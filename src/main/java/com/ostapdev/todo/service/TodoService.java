package com.ostapdev.todo.service;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.model.Task;

import java.util.LinkedHashMap;
import java.util.Map;

public class TodoService implements TodoDao {
    private final Map<Integer,Task> tasks = new LinkedHashMap<>();

    @Override
    public void add(String taskDescription) {
        tasks.put(1,new Task(taskDescription,false));
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

    private void printTask(boolean isAllMark,Task task,Integer taskId){
        if (isAllMark) System.out.println(taskId + ". " + (task.isDone() ? "X " : "") + task.getTaskDescription());

        else {
            if (!task.isDone()) {
                System.out.println(taskId + ". " + task.getTaskDescription());
            }
        }
    }
}
