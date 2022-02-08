package com.ostapdev.todo;

import com.ostapdev.todo.dto.account.CreateAccountDtoRequest;
import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.UserAlreadyExistException;
import com.ostapdev.todo.model.Role;
import com.ostapdev.todo.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    private AccountService service;

    @Test
    public void AddAccount_UsernameUser_ThrowException(){
        CreateAccountDtoRequest request = new CreateAccountDtoRequest("user","1", Role.USER);
        assertThrows(UserAlreadyExistException.class,()->service.addAccount(request));
    }

    @Test
    public void GetAccountByUsername_UsernameUser1_ThrowException(){
        assertThrows(NoSuchDataException.class,()->service.getAccountByUsername("User231231"));
    }
}
