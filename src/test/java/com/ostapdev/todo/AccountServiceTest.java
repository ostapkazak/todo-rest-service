package com.ostapdev.todo;

import com.ostapdev.todo.dto.account.CreateAccountDtoRequest;
import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.UserAlreadyExistException;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.repo.AccountRepo;
import com.ostapdev.todo.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AccountServiceTest {
    @MockBean
    private AccountRepo repo;
    @Autowired
    private AccountService service;

    @Test
    public void AddAccount_UsernameUser_ThrowException(){
        CreateAccountDtoRequest request = new CreateAccountDtoRequest();
        request.setUsername("user");
        given(repo.findAccountByUsername(anyString())).willReturn(Optional.of(Account.builder().username("user").build()));

        assertThrows(UserAlreadyExistException.class,()->service.addAccount(request));
    }

    @Test
    public void GetAccountByUsername_UsernameUser1_ThrowException(){
        given(repo.findAccountByUsername(anyString())).willReturn(Optional.empty());

        assertThrows(NoSuchDataException.class,()->service.getAccountByUsername("User1"));
    }
}
