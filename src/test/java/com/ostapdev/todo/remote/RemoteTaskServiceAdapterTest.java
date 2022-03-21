package com.ostapdev.todo.remote;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.remoteTaskService.RemoteTaskServiceAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@WireMockTest(httpPort = 8089)
@ActiveProfiles({"test"})
public class RemoteTaskServiceAdapterTest {
    @Autowired
    private RemoteTaskServiceAdapter adapter;

    @Test
    public void GetTasks_NullParams_ReturnTaskList() throws ExecutionException, InterruptedException {
        stubFor(get(urlEqualTo("/tasks")).willReturn(okJson("{\"success\":true,\"data\":[{\"id\":4,\"text\":\"task\",\"status\":\"ACTIVE\"},{\"id\":5,\"text\":\"task\",\"status\":\"ACTIVE\"}]}")));

        List<TaskDto> tasks = adapter.getTasks(null,null).get();

        assertEquals(2, tasks.size());
    }
}
