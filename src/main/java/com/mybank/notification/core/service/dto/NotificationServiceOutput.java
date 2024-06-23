package com.mybank.notification.core.service.dto;

import com.mybank.notification.core.domain.NotificationType;

public record NotificationServiceOutput (NotificationType type, String userId, String message, int numberOfMessagesSent){}
