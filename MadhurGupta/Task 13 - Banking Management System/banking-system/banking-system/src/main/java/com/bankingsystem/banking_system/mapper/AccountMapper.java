package com.bankingsystem.banking_system.mapper;

import com.bankingsystem.banking_system.dto.account.AccountDTO;
import com.bankingsystem.banking_system.entity.Account;

public class AccountMapper {

    public static AccountDTO toDTO(Account account) {
        return AccountDTO.builder()
                .id(account.getId())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .status(account.getStatus())
                .createdAt(account.getCreatedAt())
                .updatedAt(account.getUpdatedAt())
                .userId(account.getUser() != null ? account.getUser().getId() : null)
                .build();
    }
}
