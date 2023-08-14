package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream()
                .map(AccountDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    public AccountDTO getAccountId(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(AccountDTO::new)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
