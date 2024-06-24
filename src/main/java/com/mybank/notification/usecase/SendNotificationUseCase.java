package com.mybank.notification.usecase;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.usecase.dto.SendNotificationOutput;

public interface SendNotificationUseCase {
    SendNotificationOutput send(NotificationType type, String userId, String message);
}
