package com.ostapdev.todo;

import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.repo.TaskRepo;
import com.ostapdev.todo.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;


@SpringBootTest
public class TaskServiceTest {
    @MockBean
    private TaskRepo taskRepo;
    @Autowired
    private TaskService taskService;

    @Test
    public void Toggle_TaskDoesntExist_ThrowException(){
        given(taskRepo.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(NoSuchDataException.class,()->taskService.toggle(1L));
    }
}
