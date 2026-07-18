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
    public TransferResponse transfer(TransferRequest request) {
        AccountEntity debtor = accountRepository.findByAccountNumber(request.getDebtorAccountNumber())
                .orElseThrow(() -> new FunctionalError("debtor account number " + request.getDebtorAccountNumber() + " not found"));

        AccountEntity creditor = accountRepository.findByAccountNumber(request.getCreditorAccountNumber())
                .orElseThrow(() -> new FunctionalError("creditor account number " + request.getCreditorAccountNumber() + " not found"));

        if (debtor.getBalance() < request.getAmount()) throw new FunctionalError("balance not sufficient");

        debtor.setBalance(debtor.getBalance() - request.getAmount());
        creditor.setBalance(creditor.getBalance() + request.getAmount());

        accountRepository.save(debtor);
        accountRepository.save(creditor);

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .amount(request.getAmount())
                .creditorAccount(creditor)
                .debtorAccount(debtor)
                .motif(request.getMotif())
                .build();

       return transactionRepository.save(transactionEntity).toTransferResponse();
    }
}
