package com.ostapdev.todo.dto.account;

import com.ostapdev.todo.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDtoRequest {
    private String username;
    private String password;
    private Role role;
}
