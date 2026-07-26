package com.digitalbanking.persistence.entities;

import com.digitalbanking.enums.AccountStatus;
import com.digitalbanking.persistence.entities.base.AuditEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class AccountEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String accountNumber;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private UserEntity owner;
    @Version
    private Long version;
}
