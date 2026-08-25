package com.example.library.service.security_service;

import com.example.library.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AccountSecurityService {

    private final AccountRepository accountRepository;

    public AccountSecurityService(
            AccountRepository accountRepository
    ) {
        this.accountRepository = accountRepository;
    }

    public boolean isSecurityVersionValid(
            String username,
            Long tokenVersion
    ){

        return accountRepository
                .findByUsername(username)
                .map(account ->
                        account.getVersion()
                                .equals(tokenVersion)

                )
                .orElse(false);
    }
}