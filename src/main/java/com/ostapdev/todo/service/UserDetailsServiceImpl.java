package com.ostapdev.todo.service;

import com.ostapdev.todo.exception.NoSuchDataException;
import com.ostapdev.todo.exception.UserAlreadyExistException;
import com.ostapdev.todo.model.Account;
import com.ostapdev.todo.repo.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo
                .findAccountByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found: " + username));
    }


}
