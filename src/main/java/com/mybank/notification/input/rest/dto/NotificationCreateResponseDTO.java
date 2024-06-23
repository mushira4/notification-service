package com.mybank.notification.input.rest.dto;

import com.mybank.notification.core.service.dto.NotificationServiceOutput;

public record NotificationCreateResponseDTO(
    String type,
    String userId,
    String message,
    Integer numberOfMessages){

    public NotificationCreateResponseDTO(NotificationServiceOutput notificationServiceOutput) {
        this(notificationServiceOutput.type().name(), notificationServiceOutput.userId(), notificationServiceOutput.message(), notificationServiceOutput.numberOfMessagesSent());
    }

}
