package com.digitalbanking.api;


import com.digitalbanking.dtos.TransferRequest;
import com.digitalbanking.dtos.TransferResponse;
import com.digitalbanking.services.TransferService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/banking/api/v1/transactions")
@RestController
@AllArgsConstructor
public class TransactionController {
    private final TransferService transferService;

    @PostMapping
    TransferResponse doTransfer(@RequestBody @Valid TransferRequest request) {
        return transferService.transfer(request);
    }
}
