package com.ostapdev.todo.service.task;

import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.dto.task.TaskMapper;
import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.TaskAccessException;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.repo.TaskRepo;
import com.ostapdev.todo.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepo taskRepo;
    private final AccountService accountService;
    private final TaskMapper taskMapper;

    @Override
    public void add(String taskDescription,String username) {
        taskRepo.save(new Task(taskDescription,false,accountService.getAccountByUsername(username)));
    }

    @Override
    public void toggle(Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(()->new NoSuchDataException("Задачи с id " + taskId + " нет"));
        checkTaskAuthor(task);

        task.setDone(!task.getDone());
        taskRepo.save(task);
    }

    @Override
    public List<TaskDto> getTasks(Boolean isAll, String target) {
        return taskMapper.toListOfDto(taskRepo.find(target,isAll,accountService.getAccountByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId()));
    }

    @Override
    public void delete(Long taskId) {
        Task task = taskRepo.findById(taskId).orElseThrow(()->new NoSuchDataException("Задачи с id " + taskId + " нет"));
        checkTaskAuthor(task);
        taskRepo.delete(task);
    }

    @Override
    public void edit(Long taskId, String taskDescription) {
        Task task = taskRepo.findById(taskId).orElseThrow(()->new NoSuchDataException("Задачи с id " + taskId + " нет"));
        checkTaskAuthor(task);
        task.setTaskDescription(taskDescription);

        taskRepo.save(task);
    }

    private void checkTaskAuthor(Task task){
        if (!task.getAccount().getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName())){
            throw new TaskAccessException("Доступ к данной задаче запрещён");
        }
    }
}
