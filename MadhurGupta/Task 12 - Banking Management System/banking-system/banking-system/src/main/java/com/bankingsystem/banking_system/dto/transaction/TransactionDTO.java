package com.bankingsystem.banking_system.dto.transaction;

import com.bankingsystem.banking_system.enums.TransactionStatus;
import com.bankingsystem.banking_system.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionDTO {
    private Long id;
    private Long sourceAccountId;
    private Long targetAccountId;
    private Double amount;
    private TransactionType type;
    private TransactionStatus status;
    private String description;
    private LocalDateTime timestamp;
}
