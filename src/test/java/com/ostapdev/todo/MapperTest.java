package com.ostapdev.todo;

import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.dto.account.AccountMapper;
import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.dto.task.TaskMapper;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.model.Task;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapperTest {
    private TaskMapper taskMapper = Mappers.getMapper(TaskMapper.class);
    private AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

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
