package com.ostapdev.todo.config;

import com.ostapdev.todo.dto.account.AccountMapper;
import com.ostapdev.todo.dto.task.RemoteTaskMapper;
import com.ostapdev.todo.dto.task.TaskMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;


@Configuration
@EnableAsync
public class BeanConfig {
    @Bean
    public AccountMapper accountMapper(){
        return Mappers.getMapper(AccountMapper.class);
    }

    @Bean
    public TaskMapper taskMapper(){
        return Mappers.getMapper(TaskMapper.class);
    }

    @Bean
    public RemoteTaskMapper remoteTaskMapper(){
        return Mappers.getMapper(RemoteTaskMapper.class);
    }

    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("TaskThread-");
        executor.initialize();
        return executor;
    }
}
