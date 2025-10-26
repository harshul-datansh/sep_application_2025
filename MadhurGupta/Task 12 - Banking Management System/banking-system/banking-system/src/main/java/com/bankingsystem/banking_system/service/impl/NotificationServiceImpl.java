package com.bankingsystem.banking_system.service.impl;

import com.bankingsystem.banking_system.dto.notification.NotificationDTO;
import com.bankingsystem.banking_system.entity.Notification;
import com.bankingsystem.banking_system.entity.User;
import com.bankingsystem.banking_system.entity.Transaction;
import com.bankingsystem.banking_system.repository.NotificationRepository;
import com.bankingsystem.banking_system.repository.UserRepository;
import com.bankingsystem.banking_system.repository.TransactionRepository;
import com.bankingsystem.banking_system.service.interfaces.INotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public NotificationDTO createNotification(NotificationDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = null;
        if(dto.getTransactionId() != null){
            transaction = transactionRepository.findById(dto.getTransactionId())
                    .orElseThrow(() -> new RuntimeException("Transaction not found"));
        }

        Notification notification = Notification.builder()
                .user(user)
                .message(dto.getMessage())
                .type(dto.getType())
                .readFlag(false)
                .relatedTransaction(transaction)
                .build();

        Notification saved = notificationRepository.save(notification);

        dto.setId(saved.getId());
        dto.setTimestamp(saved.getTimestamp());
        return dto;
    }

    @Override
    public List<NotificationDTO> getAllNotificationsForUser(Long userId) {
        return notificationRepository.findByUserId(userId).stream()
                .map(n -> NotificationDTO.builder()
                        .id(n.getId())
                        .userId(n.getUser().getId())
                        .message(n.getMessage())
                        .type(n.getType())
                        .readFlag(n.getReadFlag())
                        .timestamp(n.getTimestamp())
                        .transactionId(n.getRelatedTransaction() != null ? n.getRelatedTransaction().getId() : null)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDTO markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setReadFlag(true);
        Notification saved = notificationRepository.save(notification);

        return NotificationDTO.builder()
                .id(saved.getId())
                .userId(saved.getUser().getId())
                .message(saved.getMessage())
                .type(saved.getType())
                .readFlag(saved.getReadFlag())
                .timestamp(saved.getTimestamp())
                .transactionId(saved.getRelatedTransaction() != null ? saved.getRelatedTransaction().getId() : null)
                .build();
    }
}
