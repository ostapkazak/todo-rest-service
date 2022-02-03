package com.ostapdev.todo;

import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.dto.account.AccountMapper;
import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.dto.task.TaskMapper;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MapperTest {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void ToDto_TaskDescriptionTask1_Equal(){
        Task task = new Task();
        task.setTaskDescription("Task1");

        TaskDto dto = taskMapper.toDto(task);

        assertEquals(dto.getTaskDescription(),task.getTaskDescription());
    }

    @Test
    public void ToDto_AccountNameUser1_Equal(){
        Account account = new Account();
        account.setUsername("User1");

        AccountDto dto = accountMapper.toDto(account);

        assertEquals(dto.getUsername(),account.getUsername());
    }
}
