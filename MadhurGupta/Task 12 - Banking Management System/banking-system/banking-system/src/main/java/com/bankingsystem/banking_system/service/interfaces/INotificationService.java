package com.bankingsystem.banking_system.service.interfaces;

import com.bankingsystem.banking_system.dto.notification.NotificationDTO;

import java.util.List;

public interface INotificationService {

    NotificationDTO createNotification(NotificationDTO notificationDTO);

    List<NotificationDTO> getAllNotificationsForUser(Long userId);

    NotificationDTO markAsRead(Long id);
}
