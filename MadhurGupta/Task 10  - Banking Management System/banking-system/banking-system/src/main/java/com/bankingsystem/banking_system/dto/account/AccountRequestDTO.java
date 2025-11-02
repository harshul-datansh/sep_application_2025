package com.bankingsystem.banking_system.dto.account;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AccountRequestDTO {
    private Long userId;           // The user to whom account will be linked
    private String accountType;    // "SAVINGS" or "CURRENT"
    private BigDecimal balance;    // Optional: initial balance
}
