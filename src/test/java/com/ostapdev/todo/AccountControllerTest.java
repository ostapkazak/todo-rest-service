package com.ostapdev.todo;

import com.ostapdev.todo.controller.AccountController;
import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.model.Role;
import com.ostapdev.todo.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @MockBean
    private AccountService accountService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void GetTasks_AdminRole_Return200() throws Exception {
        given(accountService.getAllAccounts())
            .willReturn(Arrays.asList(new AccountDto(1L,"user",Role.USER),new AccountDto(2L,"user2",Role.USER)));
        mockMvc.perform(get("/api/accounts/"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    public void GetAccounts_UserRole_Return403() throws Exception {
        mockMvc.perform(get("/api/accounts/"))
            .andExpect(status().isForbidden());
    }
}
