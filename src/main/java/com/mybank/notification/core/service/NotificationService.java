package com.mybank.notification.core.service;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.core.service.dto.NotificationServiceOutput;

public interface NotificationService {
    NotificationServiceOutput send(NotificationType type, String userId, String message);
}
