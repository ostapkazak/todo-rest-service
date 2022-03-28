package com.ostapdev.todo.service.task;

import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.dto.task.TaskMapper;
import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.TaskAccessException;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.remoteTaskService.RemoteTaskServiceAdapter;
import com.ostapdev.todo.repo.TaskRepo;
import com.ostapdev.todo.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final AccountService accountService;
    private final TaskMapper taskMapper;
    private final RemoteTaskServiceAdapter remoteTaskServiceAdapter;

    @Override
    public void add(String taskDescription, String username, Boolean remote) {
        if (remote){
            remoteTaskServiceAdapter.createTask(taskDescription);
        } else {
            taskRepo.save(new Task(taskDescription,false,accountService.getAccountByUsername(username)));
        }
    }

    @Override
    public void toggle(Long taskId, Boolean remote) {
        if (remote){
           TaskDto taskDto = remoteTaskServiceAdapter.getTaskById(taskId.toString());
           if (taskDto != null) remoteTaskServiceAdapter.toggleTask(taskId.toString(),!taskDto.getDone());
           else throw new  NoSuchDataException("Задачи с id " + taskId + " нет");
        }

        else {
            Task task = taskRepo.findById(taskId).orElseThrow(()->new NoSuchDataException("Задачи с id " + taskId + " нет"));
            checkTaskAuthor(task);

            task.setDone(!task.getDone());
            taskRepo.save(task);
        }
    }

    @Override
    public List<TaskDto> getTasks(Boolean isAll, String target) throws ExecutionException, InterruptedException {
        CompletableFuture<List<TaskDto>> futureRemoteTasks = remoteTaskServiceAdapter.getTasks(isAll,target);
        CompletableFuture<List<Task>> futureLocalTasks = taskRepo.find(target,isAll,accountService.getAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId());

        CompletableFuture.allOf(futureRemoteTasks,futureLocalTasks).join();
        List<TaskDto> tasks = futureRemoteTasks.get();
        tasks.addAll(taskMapper.toListOfDto(futureLocalTasks.get()));
        return tasks;
    }

    @Override
    public void delete(Long taskId, Boolean remote) {
        if (remote) {
            remoteTaskServiceAdapter.deleteTask(taskId.toString());
        }else {
            Task task = taskRepo.findById(taskId).orElseThrow(()->new NoSuchDataException("Задачи с id " + taskId + " нет"));
            checkTaskAuthor(task);
            taskRepo.delete(task);
        }
    }

    @Override
    public void edit(Long taskId, String taskDescription, Boolean remote) {
        if (remote){
            remoteTaskServiceAdapter.editTask(taskId.toString(),taskDescription);
        }else {
            Task task = taskRepo.findById(taskId).orElseThrow(()->new NoSuchDataException("Задачи с id " + taskId + " нет"));
            checkTaskAuthor(task);
            task.setTaskDescription(taskDescription);

            taskRepo.save(task);
        }
    }

    private void checkTaskAuthor(Task task){
        if (!task.getAccount().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new TaskAccessException("Доступ к данной задаче запрещён");
        }
    }
}
