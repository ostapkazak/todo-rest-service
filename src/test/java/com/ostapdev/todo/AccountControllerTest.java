package com.ostapdev.todo;

import com.ostapdev.todo.controller.AccountController;
import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.model.Role;
import com.ostapdev.todo.service.account.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

public class AccountControllerTest {
    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;
    private AutoCloseable closeable;

    @BeforeEach
    public void open(){
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    public void GetAccount_AccountNameUser22_ReturnNotNull()  {
        when(accountService.getAccountDtoByUsername("user22")).thenReturn(new AccountDto(1L,"user22", Role.USER));
        assertNotEquals(accountController.getAccountByUsername("user22"),null);
    }
}
