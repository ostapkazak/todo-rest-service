package com.ostapdev.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class BeanConfig {
    @Bean
    public BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }
}
