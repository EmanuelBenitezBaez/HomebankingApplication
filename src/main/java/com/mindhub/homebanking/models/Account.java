package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientId")//columna que los va a unir
    private Client clientId;
    private String number;
    private LocalDate date;
    private double balance;

    public  Account() {

    }

    public Account(String number, LocalDate date, double balance) {
        this.clientId = clientId;
        this.number = number;
        this.date = date;
        this.balance = balance;
    }

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client client) {
        this.clientId = client;
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
}
