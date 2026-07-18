package com.digitalbanking.persistence.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    private AccountEntity debtorAccount;
    @ManyToOne
    private AccountEntity creditorAccount;
    private Double amount;
    private String motif;
}
