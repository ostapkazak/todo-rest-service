package com.ostapdev.todo.controller;

import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.dto.account.CreateAccountDtoRequest;
import com.ostapdev.todo.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/accounts/")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<AccountDto> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @GetMapping("{username}")
    public AccountDto getAccountByUsername(@PathVariable String username){
        return accountService.getAccountDtoByUsername(username);
    }

    @PostMapping
    public void addAccount(@RequestBody @Valid CreateAccountDtoRequest request){
        accountService.addAccount(request);
    }

    @PatchMapping
    public void updateAccount(@RequestBody @Valid AccountDto accountDto){
        accountService.updateAccount(accountDto);
    }

    @DeleteMapping("{username}")
    public void deleteAccount(@PathVariable String username){
        accountService.deleteAccount(username);
    }
}
