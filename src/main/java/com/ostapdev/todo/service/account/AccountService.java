package com.ostapdev.todo.service.account;

import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.dto.account.CreateAccountDtoRequest;
import com.ostapdev.todo.model.Account;

import java.util.List;

public interface AccountService {
    List<AccountDto> getAllAccounts();

    AccountDto getAccountDtoByUsername(String username);

    Account getAccountByUsername(String username);

    void addAccount(CreateAccountDtoRequest request);

    void updateAccount(AccountDto accountDto);

    void deleteAccount(String username);
}
