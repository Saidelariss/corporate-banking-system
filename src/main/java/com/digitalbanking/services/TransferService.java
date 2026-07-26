package com.digitalbanking.services;

import com.digitalbanking.dtos.TransferRequest;
import com.digitalbanking.dtos.TransferResponse;
import com.digitalbanking.enums.AccountStatus;
import com.digitalbanking.exceptions.FunctionalError;
import com.digitalbanking.persistence.entities.AccountEntity;
import com.digitalbanking.persistence.entities.TransactionEntity;
import com.digitalbanking.persistence.entities.UserEntity;
import com.digitalbanking.persistence.repositories.AccountRepository;
import com.digitalbanking.persistence.repositories.TransactionRepository;
import com.digitalbanking.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TransferService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Transactional
    public TransferResponse transfer(TransferRequest request) {
        if (request.getDebtorAccountNumber().equals(request.getCreditorAccountNumber()))
            throw new FunctionalError("debtor and creditor accounts must be different");

        AccountEntity debtor = accountRepository.findByAccountNumber(request.getDebtorAccountNumber())
                .orElseThrow(() -> new FunctionalError("debtor account number " + request.getDebtorAccountNumber() + " not found"));

        AccountEntity creditor = accountRepository.findByAccountNumber(request.getCreditorAccountNumber())
                .orElseThrow(() -> new FunctionalError("creditor account number " + request.getCreditorAccountNumber() + " not found"));

        String currentUserPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity currentUser = userRepository.findByPhoneNumber(currentUserPhoneNumber)
                .orElseThrow(() -> new FunctionalError("authenticated user not found"));

        if (debtor.getOwner() == null || !debtor.getOwner().getId().equals(currentUser.getId()))
            throw new FunctionalError("you are not authorized to transfer from this account");

        if (debtor.getStatus() != AccountStatus.ACTIVE)
            throw new FunctionalError("debtor account is not active");
        if (creditor.getStatus() != AccountStatus.ACTIVE)
            throw new FunctionalError("creditor account is not active");

        if (debtor.getBalance().compareTo(request.getAmount()) < 0) throw new FunctionalError("balance not sufficient");

        debtor.setBalance(debtor.getBalance().subtract(request.getAmount()));
        creditor.setBalance(creditor.getBalance().add(request.getAmount()));

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
