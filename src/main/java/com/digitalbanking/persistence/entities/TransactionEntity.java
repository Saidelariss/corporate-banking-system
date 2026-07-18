package com.digitalbanking.persistence.entities;

import com.digitalbanking.persistence.entities.base.AuditEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class TransactionEntity extends AuditEntity {
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
