package com.ostapdev.todo.config;

import com.ostapdev.todo.dto.account.AccountMapper;
import com.ostapdev.todo.dto.task.TaskMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {
    @Bean
    public AccountMapper accountMapper(){
        return Mappers.getMapper(AccountMapper.class);
    }

    @Bean
    public TaskMapper taskMapper(){
        return Mappers.getMapper(TaskMapper.class);
    }
}
