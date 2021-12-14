package com.ostapdev.todo.dto.account;

import com.ostapdev.todo.model.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AccountDto {
    private Long id;
    private String username;
    private Role role;
}
