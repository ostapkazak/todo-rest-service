package com.ostapdev.todo;

import com.ostapdev.todo.dto.account.CreateAccountDtoRequest;
import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.UserAlreadyExistException;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.model.Role;
import com.ostapdev.todo.repo.AccountRepo;
import com.ostapdev.todo.service.account.AccountServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AccountServiceTest {
    @Mock
    private AccountRepo accountRepo;
    @InjectMocks
    private AccountServiceImpl service;
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
    public void AddAccount_UsernameUser_ThrowException(){
        when(accountRepo.findAccountByUsername("user")).thenReturn(Optional.of(Account.builder().username("user").build()));
        CreateAccountDtoRequest request = new CreateAccountDtoRequest("user","1", Role.USER);
        assertThrows(UserAlreadyExistException.class,()->service.addAccount(request));
    }

    @Test
    public void GetAccountByUsername_UsernameUser_ThrowException(){
        when(accountRepo.findAccountByUsername("user")).thenReturn(Optional.empty());
        assertThrows(NoSuchDataException.class,()->service.getAccountByUsername("user"));
    }
}
