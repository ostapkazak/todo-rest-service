package com.ostapdev.todo.remoteTaskService;

import com.ostapdev.todo.config.RemoteTaskServiceConfig;
import com.ostapdev.todo.dto.task.RemoteTaskMapper;
import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.remoteTaskService.model.CreateTaskRequest;
import com.ostapdev.todo.remoteTaskService.model.RemoteTaskServiceResponse;
import com.ostapdev.todo.remoteTaskService.model.TaskListResponse;
import com.ostapdev.todo.remoteTaskService.model.TaskResponse;
import com.ostapdev.todo.remoteTaskService.model.ToggleTaskRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RemoteTaskServiceAdapter {
    private final RestTemplate restTemplate;
    private final RemoteTaskMapper taskMapper;

    public RemoteTaskServiceAdapter(RestTemplateBuilder builder, RemoteTaskServiceConfig config, RemoteTaskMapper taskMapper) {
        this.taskMapper = taskMapper;
        this.restTemplate = builder
            .rootUri(config.getUrl())
            .setConnectTimeout(Duration.ofSeconds(config.getConnectTimeout()))
            .setReadTimeout(Duration.ofSeconds(config.getReadTimeout()))
            .errorHandler(new RemoteTaskServiceErrorHandler())
            .basicAuthentication(config.getUsername(),config.getPassword())
            .build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    public List<TaskDto> getTasks(Boolean active,String query){
        if (active != null) active = !active;
        Map<String,Object> params = new HashMap<>();
        params.put("active",active);
        params.put("query",query);
        return taskMapper.toListOfDto(callService("/tasks",Collections.emptyMap(),HttpMethod.GET,null,TaskListResponse.class,params).getData());
    }

    public TaskDto getTaskById(String id){
        return taskMapper.toDto(callService("/tasks/{id}",Map.of("id",id),HttpMethod.GET,null,TaskResponse.class,Collections.emptyMap()).getData());
    }

    public TaskDto toggleTask(String id,Boolean done){
        return taskMapper.toDto(callService("/tasks/{id}/done",Map.of("id",id),HttpMethod.PATCH,new ToggleTaskRequest(done),TaskResponse.class,Collections.emptyMap()).getData());
    }

    public TaskDto deleteTask(String id){
        return taskMapper.toDto(callService("/tasks/{id}",Map.of("id",id),HttpMethod.DELETE,null,TaskResponse.class,Collections.emptyMap()).getData());
    }

    public TaskDto createTask(String text){
        return taskMapper.toDto(callService("/tasks/",Collections.emptyMap(),HttpMethod.POST,new CreateTaskRequest(text), TaskResponse.class,Collections.emptyMap()).getData());
    }

    public TaskDto editTask(String id,String text){
        return taskMapper.toDto(callService("/tasks/{id}/edit",Map.of("id",id),HttpMethod.PATCH,new CreateTaskRequest(text),TaskResponse.class,Collections.emptyMap()).getData());
    }

    private <T extends RemoteTaskServiceResponse> T callService(String url, Map<String,String> pathVariables, HttpMethod httpMethod, Object body, Class<T> responseClass, Map<String,Object> params){
        UriTemplate uriTemplate = new UriTemplate(url);
        URI uri = uriTemplate.expand(pathVariables);

        ResponseEntity<T> response = restTemplate.exchange(uri.getPath(), httpMethod, buildEntity(body), responseClass,params);

        if (response.getBody() != null){
            return response.getBody();
        }

        throw new RemoteTaskServiceException("Remote service error response");
    }

    private HttpEntity<Object> buildEntity(Object body){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(body,headers);
    }
}
