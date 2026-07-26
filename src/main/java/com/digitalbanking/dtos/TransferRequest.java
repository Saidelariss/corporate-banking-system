package com.digitalbanking.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TransferRequest {
    @NotBlank(message = "debtor account number must not be blank")
    private String debtorAccountNumber;
    @NotBlank(message = "creditor account number must not be blank")
    private String creditorAccountNumber;
    @NotNull(message = "amount must not be null")
    @Positive(message = "amount must be positive")
    private Double amount;
    @Size(max = 140, message = "motif must not exceed 140 characters")
    private String motif;
}
