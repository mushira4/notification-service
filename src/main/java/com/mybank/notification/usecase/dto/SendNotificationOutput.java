package com.mybank.notification.usecase.dto;

import com.mybank.notification.core.domain.NotificationType;

public record SendNotificationOutput(NotificationType type, String userId, String message, int numberOfMessagesSent){}
