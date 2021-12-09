package com.ostapdev.todo.service.account;

import com.ostapdev.todo.dto.account.AccountDto;
import com.ostapdev.todo.dto.account.AccountMapper;
import com.ostapdev.todo.dto.account.CreateAccountDtoRequest;
import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.UserAlreadyExistException;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper accountMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountMapper.toListOfDto(accountRepo.findAll());
    }

    @Override
    public AccountDto getAccountDtoByUsername(String username) {
        return accountMapper.toDto(getAccountByUsername(username));
    }

    @Override
    public Account getAccountByUsername(String username) {
        return accountRepo
                .findAccountByUsername(username)
                .orElseThrow(()->new NoSuchDataException("User with username " + username + " does not exist"));
    }

    @Override
    public void addAccount(CreateAccountDtoRequest request){
        if (accountRepo.findAccountByUsername(request.getUsername()).isPresent()){
            throw new UserAlreadyExistException("User with username " + request.getUsername() + " already exist");
        }

        accountRepo.save(Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build());
    }

    @Override
    public void updateAccount(AccountDto accountDto){
        Account accountFromDB = accountRepo
                .findById(accountDto.getId())
                .orElseThrow(()->new NoSuchDataException("User with id " + accountDto.getId() + " does not exist"));

        accountRepo.save(accountMapper.updateAccountFromDto(accountFromDB,accountDto));
    }

    @Override
    public void deleteAccount(String username) {
        getAccountByUsername(username);

        accountRepo.deleteAccountByUsername(username);
    }
}
