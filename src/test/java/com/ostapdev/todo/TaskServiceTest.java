package com.ostapdev.todo;

import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void Toggle_TaskDoesntExist_ThrowException(){
        assertThrows(NoSuchDataException.class,()->taskService.toggle(-1L));
    }
}
