package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ClientLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;


    private double amount;    // Monto del préstamo
    private int payments;    // Número de pagos

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loan_id")
    private Loan loan;


    public ClientLoan() {

    }

    public ClientLoan(double amount, int payments, Loan loan, Client client) {
        this.amount = amount;
        this.payments = payments;
        this.loan = loan;
        this.client = client;
    }

    public Long getId() {
        return id;
    }


    public double getAmount() {
        return amount;
    }


    public int getPayments() {
        return payments;
    }



    public Client getClient() {
        return client;
    }



    public Loan getLoan() {
        return loan;
    }




}
