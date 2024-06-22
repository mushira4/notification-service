package com.mybank.notification.core.service;

import com.mybank.notification.core.domain.NotificationType;

public interface NotificationService {
    void send(NotificationType type, String userId, String message);
}
