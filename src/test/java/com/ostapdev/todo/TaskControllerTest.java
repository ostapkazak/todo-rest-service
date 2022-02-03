package com.ostapdev.todo;

import com.ostapdev.todo.controller.TaskController;
import com.ostapdev.todo.dto.task.TaskDto;
import com.ostapdev.todo.service.task.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @MockBean
    private TaskService taskService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void GetTasks_UserRole_Return200() throws Exception {
        given(taskService.getTasks(anyBoolean(),anyString())).willReturn(Arrays.asList(new TaskDto(1L,"Task1",false),new TaskDto(2L,"Task2",false)));
        mockMvc.perform(get("/api/tasks/"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void GetTasks_AdminRole_Return403() throws Exception {
        mockMvc.perform(get("/api/tasks/"))
            .andExpect(status().isForbidden());
    }


}
