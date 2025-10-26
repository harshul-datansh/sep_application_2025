package com.bankingsystem.banking_system.dto.notification;

import com.bankingsystem.banking_system.enums.NotificationType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {
    private Long id;
    private Long userId;
    private String message;
    private NotificationType type;
    private Boolean readFlag;
    private LocalDateTime timestamp;
    private Long transactionId; // optional
}
