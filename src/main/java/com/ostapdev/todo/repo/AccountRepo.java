package com.ostapdev.todo.repo;

import com.ostapdev.todo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;


public interface AccountRepo extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByUsername(String username);

    @Transactional
    void deleteAccountByUsername(String username);
}
