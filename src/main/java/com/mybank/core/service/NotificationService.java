package com.mybank.core.service;

import com.mybank.core.domain.NotificationType;

public interface NotificationService {
    void send(NotificationType type, String userId, String message);
}
