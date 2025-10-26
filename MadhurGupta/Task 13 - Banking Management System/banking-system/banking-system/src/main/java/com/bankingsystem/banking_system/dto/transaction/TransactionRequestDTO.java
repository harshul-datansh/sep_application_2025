package com.bankingsystem.banking_system.dto.transaction;

import lombok.Data;

@Data
public class TransactionRequestDTO {
    private Long sourceAccountId; // For withdrawal/transfer
    private Long targetAccountId; // For deposit/transfer
    private Double amount;
    private String description;
}
