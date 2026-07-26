package com.digitalbanking.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class TransferResponse {
    private UUID id;
    private String debtorAccountNumber;
    private String creditorAccountNumber;
    private Double amount;
    private String motif;
}
