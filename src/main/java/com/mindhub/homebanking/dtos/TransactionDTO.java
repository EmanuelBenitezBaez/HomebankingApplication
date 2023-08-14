package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private String description;
    private Double amount;
    private LocalDate date;
    private TransactionType type;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.date = transaction.getDate();
        this.type = transaction.getType();
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }
}
