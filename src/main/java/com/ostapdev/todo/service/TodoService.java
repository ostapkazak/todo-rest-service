package com.ostapdev.todo.service;

import com.ostapdev.todo.dao.TodoDao;
import com.ostapdev.todo.model.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class TodoService implements TodoDao {
    private final Map<Integer,Task> tasks = new HashMap<>();
    private Integer lastTaskId = 0;

    private static TodoService instance;

    private TodoService() {
    }

    public static TodoService getInstance(){
        if (instance == null) instance = new TodoService();
        return instance;
    }

    @Override
    public void add(String taskDescription) {
        tasks.put(lastTaskId + 1,new Task(taskDescription,false));
        lastTaskId++;
    }

    @Override
    public void toggle(Integer taskId) {
        Task task = tasks.get(taskId);

        if (task == null){
            log.error("Задачи с таким идентификатором нет");
            System.out.println("Задачи с таким идентификатором нет");
        }

        else {
            task.setDone(!task.isDone());
            tasks.put(taskId,task);
        }
    }

    @Override
    public void print(boolean isAll) {
        if (tasks.isEmpty()){
            log.error("Список задач пуст");
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
            log.error("Задачи с таким идентификатором нет");
            System.out.println("Задачи с таким идентификатором нет");
        }

        else {
            tasks.remove(taskId);
        }
    }

    @Override
    public void edit(Integer taskId, String taskDescription) {
        Task task = tasks.get(taskId);

        if (task == null){
            log.error("Задачи с таким идентификатором нет");
            System.out.println("Задачи с таким идентификатором нет");
        }

        else {
            task.setTaskDescription(taskDescription);
            tasks.put(taskId,task);
        }
    }

    @Override
    public void search(String substring) {
        tasks.entrySet().stream()
                .filter(e -> e.getValue().getTaskDescription().contains(substring))
                .forEach(e->printTask(e.getValue(),e.getKey()));
    }

    private void printTask(Task task,Integer taskId){
        log.debug("out: " + taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
        System.out.println(taskId + ". " + (task.isDone() ? "[X]" : "[ ]") + task.getTaskDescription());
    }
}
