package com.digitalbanking.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class TransferResponse {
    private UUID id;
    private String debtorAccountNumber;
    private String creditorAccountNumber;
    private Double amount;
    private String motif;
}
