package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;


import java.time.LocalDate;
public class AccountDTO {

    private  Long id;
    private  Long clientId;
    private  String number;
    private  LocalDate getDate;
    private  double balance;

    public AccountDTO(Account account) {
        id = account.getId();
        clientId = account.getClientId().getId();
        number = account.getNumber();
        getDate = account.getDate();
        balance = account.getBalance();
    }

    public Long getId() {
        return id;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return getDate;
    }

    public double getBalance() {
        return balance;
    }
}
