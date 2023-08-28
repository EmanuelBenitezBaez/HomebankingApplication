package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;

import com.mindhub.homebanking.models.Account;

import com.mindhub.homebanking.models.AccountType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import java.util.List;


import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.AccountUtils.accountNumber;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private  AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;




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
                .orElseThrow((null));
    }


    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> newAccount (Authentication authentication, AccountType accountType) {
        Client client=clientRepository.findByEmail(authentication.getName());
        if (client.getAccount().size()>=3) {
            return new ResponseEntity<>("It has already reached the limit of 3 accounts", HttpStatus.BAD_REQUEST);
        }
        Account newAccount= new Account(accountNumber(accountRepository), LocalDate.now(),0.00,accountType);
        client.addAccounts(newAccount);
        accountRepository.save(newAccount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }





}
