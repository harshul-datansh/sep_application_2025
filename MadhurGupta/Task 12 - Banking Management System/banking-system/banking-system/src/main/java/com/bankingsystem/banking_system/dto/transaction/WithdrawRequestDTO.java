package com.bankingsystem.banking_system.dto.transaction;

import lombok.Data;

@Data
public class WithdrawRequestDTO {
    private Double amount;
    private String description;
}
