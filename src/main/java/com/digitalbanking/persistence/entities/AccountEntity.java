package com.digitalbanking.persistence.entities;

import com.digitalbanking.enums.AccountStatus;
import com.digitalbanking.persistence.entities.base.AuditEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class AccountEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String accountNumber;
    private Double balance;
    private AccountStatus status;
    @ManyToOne
    private UserEntity owner;
}
