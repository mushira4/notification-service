package com.mybank.notification.input.rest.dto;

import com.mybank.notification.usecase.dto.SendNotificationOutput;

public record NotificationCreateResponseDTO(
    String type,
    String userId,
    String message,
    Integer numberOfMessages){

    public NotificationCreateResponseDTO(SendNotificationOutput sendNotificationOutput) {
        this(sendNotificationOutput.type().name(), sendNotificationOutput.userId(), sendNotificationOutput.message(), sendNotificationOutput.numberOfMessagesSent());
    }

}
