package com.ostapdev.todo.dto.account;

import com.ostapdev.todo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountDtoRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Role role;
}
