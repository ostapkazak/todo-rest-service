package com.ostapdev.todo.controller;

import com.ostapdev.todo.dto.TaskDto;
import com.ostapdev.todo.model.Task;
import com.ostapdev.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tasks/")
@Slf4j
public class TaskController {
    private final TaskService service;

    @GetMapping
    public Map<Integer, Task> getTasks(@RequestParam(name = "isAll",required = false) Boolean isAll,@RequestParam(name = "target",required = false) String target){
        return service.getTasks(isAll,target);
    }

    @PostMapping
    public void addTask(@RequestBody TaskDto dto){
        log.debug("New task: " + dto.getTaskDescription());
        service.add(dto);
    }

    @PatchMapping("{id}")
    public void editTask(@PathVariable Integer id,@RequestBody TaskDto task){
        log.debug("Edit task " + id + " - " + task.getTaskDescription());
        service.edit(id,task.getTaskDescription());
    }

    @PatchMapping("{id}/toggle")
    public void toggleTask(@PathVariable Integer id){
        log.debug("Toggle task " + id);
        service.toggle(id);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Integer id){
        log.debug("Delete task " + id);
        service.delete(id);
    }
}
