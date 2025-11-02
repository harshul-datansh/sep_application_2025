package com.bankingsystem.banking_system.dto.transaction;

import lombok.Data;

@Data
public class DepositRequestDTO {
    private Double amount;
    private String description;
}
