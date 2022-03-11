package com.ostapdev.todo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "remote-service")
@Getter
@Setter
public class RemoteTaskServiceConfig {
    private String url;
    private String username;
    private String password;
    private final long connectTimeout = 5;
    private final long readTimeout = 15;
}
