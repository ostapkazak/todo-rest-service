package com.ostapdev.todo.dto.account;

import com.ostapdev.todo.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper instance = Mappers.getMapper(AccountMapper.class);

    List<AccountDto> toListOfDto(List<Account> accounts);

    AccountDto toDto(Account account);

    Account toEntity(AccountDto accountDto);

    default Account updateAccountFromDto(Account account,AccountDto dto){
        account.setUsername(dto.getUsername());
        account.setRole(dto.getRole());

        return account;
    }
}
