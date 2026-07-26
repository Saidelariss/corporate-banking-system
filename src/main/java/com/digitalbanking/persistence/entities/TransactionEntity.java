package com.digitalbanking.persistence.entities;

import com.digitalbanking.dtos.TransferResponse;
import com.digitalbanking.persistence.entities.base.AuditEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    public TransferResponse toTransferResponse() {
        return TransferResponse.builder()
                .id(id)
                .amount(amount)
                .creditorAccountNumber(creditorAccount.getAccountNumber())
                .debtorAccountNumber(debtorAccount.getAccountNumber())
                .motif(motif)
                .build();

    }
}
