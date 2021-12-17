package com.ostapdev.todo.controller;

import com.ostapdev.todo.dto.task.CreateTaskDtoRequest;
import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks/")
@Slf4j
public class TaskController {
    private final TaskService service;

    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(name = "isAll",required = false) Boolean isAll
            , @RequestParam(name = "target",required = false) String target){
        return service.getTasks(isAll,target);
    }

    @PostMapping
    public void addTask(@Valid @RequestBody CreateTaskDtoRequest request, @AuthenticationPrincipal Account account){
        log.debug("New task: {}", request.getTaskDescription());
        service.add(request.getTaskDescription(), account.getUsername());
    }

    @PatchMapping("{id}")
    public void editTask(@PathVariable @Min(1) Long id, @Valid @RequestBody TaskDto task){
        log.debug("Edit task {} - {}", id, task.getTaskDescription());
        service.edit(id,task.getTaskDescription());
    }

    @PatchMapping("{id}/done")
    public void toggleTask(@PathVariable @Min(1) Long id){
        log.debug("Toggle task {}", id);
        service.toggle(id);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable @Min(1) Long id){
        log.debug("Delete task {}", id);
        service.delete(id);
    }
}
