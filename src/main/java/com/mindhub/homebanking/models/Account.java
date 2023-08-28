package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client")
    private Client client;
    private String number;
    private LocalDate date;
    private double balance;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private Set<Transaction> transactions = new HashSet<>();

    private AccountType accountType;
    //CONSTRUCTORS
    public Account() {

    }

    public Account(String number, LocalDate date, double balance,AccountType accountType) {

        this.number = number;
        this.date = date;
        this.balance = balance;
        this.accountType = accountType;
    }

    // GETTERS AND SETTERS

    public void addTransaction(Transaction transaction) {
        transaction.setAccountId(this);
        transactions.add(transaction);
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClientId(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setClient(Client client){
    this.client =client;
}



}
