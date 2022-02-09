package com.ostapdev.todo;

import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.repo.TaskRepo;
import com.ostapdev.todo.service.task.TaskServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TaskServiceTest {
    @Mock
    private TaskRepo taskRepo;
    @InjectMocks
    private TaskServiceImpl taskService;
    private AutoCloseable closeable;

    @BeforeEach
    public void open(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    public void Toggle_TaskDoesntExist_ThrowException(){
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(NoSuchDataException.class,()->taskService.toggle(1L));
    }
}
