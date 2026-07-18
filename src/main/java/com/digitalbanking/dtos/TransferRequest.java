package com.digitalbanking.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransferRequest {
    @NotBlank(message = "debtor account number must not be blank")
    private String debtorAccountNumber;
    @NotBlank(message = "creditor account number must not be blank")
    private String creditorAccountNumber;
    private Double amount;
    private String motif;
}
