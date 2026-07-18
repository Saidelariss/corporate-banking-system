package com.digitalbanking.services;

import com.digitalbanking.dtos.TransferRequest;
import com.digitalbanking.dtos.TransferResponse;
import com.digitalbanking.exceptions.FunctionalError;
import com.digitalbanking.persistence.entities.AccountEntity;
import com.digitalbanking.persistence.entities.TransactionEntity;
import com.digitalbanking.persistence.repositories.AccountRepository;
import com.digitalbanking.persistence.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    @Transactional
    public TransferResponse doTransfer(TransferRequest request) {
        AccountEntity debtorAccountEntity = accountRepository.findByAccountNumber(request.getDebtorAccountNumber())
                .orElseThrow(() -> new FunctionalError("debtor account number " + request.getDebtorAccountNumber() + " not found"));

        AccountEntity creditorAccountEntity = accountRepository.findByAccountNumber(request.getCreditorAccountNumber())
                .orElseThrow(() -> new FunctionalError("creditor account number " + request.getCreditorAccountNumber() + " not found"));

        if (debtorAccountEntity.getBalance() < request.getAmount()) throw new FunctionalError("balance not sufficient");

        debtorAccountEntity.setBalance(debtorAccountEntity.getBalance() - request.getAmount());
        creditorAccountEntity.setBalance(creditorAccountEntity.getBalance() + request.getAmount());

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .amount(request.getAmount())
                .creditorAccount(creditorAccountEntity)
                .debtorAccount(debtorAccountEntity)
                .motif(request.getMotif())
                .build();

       return transactionRepository.save(transactionEntity).toTransferResponse();
    }
}
