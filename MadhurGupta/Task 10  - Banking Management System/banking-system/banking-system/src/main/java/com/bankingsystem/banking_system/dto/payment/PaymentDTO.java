package com.bankingsystem.banking_system.dto.payment;

import com.bankingsystem.banking_system.enums.PaymentStatus;
import com.bankingsystem.banking_system.enums.PaymentType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private Long accountId;
    private Double amount;
    private String gatewayTransactionId;
    private PaymentStatus status;
    private PaymentType paymentType;
    private String description;
}
