package com.ostapdev.todo.controller;

import com.ostapdev.todo.dto.task.CreateTaskDtoRequest;
import com.ostapdev.todo.dto.task.EditTaskRequest;
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
            , @RequestParam(name = "target",required = false) String target,@RequestParam(name = "remote",required = false,defaultValue = "false") Boolean remote){
        return service.getTasks(isAll,target,remote);
    }

    @PostMapping
    public void addTask(@Valid @RequestBody CreateTaskDtoRequest request, @AuthenticationPrincipal Account account
        ,@RequestParam(name = "remote",required = false,defaultValue = "false") Boolean remote){
        log.debug("New task: {}", request.getTaskDescription());
        service.add(request.getTaskDescription(), account.getUsername(),remote);
    }

    @PatchMapping("{id}")
    public void editTask(@PathVariable @Min(1) Long id, @Valid @RequestBody EditTaskRequest task, @RequestParam(name = "remote",required = false,defaultValue = "false") Boolean remote){
        log.debug("Edit task {} - {}", id, task.getDescription());
        service.edit(id,task.getDescription(),remote);
    }

    @PatchMapping("{id}/done")
    public void toggleTask(@PathVariable @Min(1) Long id,@RequestParam(name = "remote",required = false,defaultValue = "false") Boolean remote){
        log.debug("Toggle task {}", id);
        service.toggle(id,remote);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable @Min(1) Long id,@RequestParam(name = "remote",required = false,defaultValue = "false") Boolean remote){
        log.debug("Delete task {}", id);
        service.delete(id,remote);
    }
}
