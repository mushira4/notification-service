package com.mybank.notification.input.rest;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.usecase.SendNotificationUseCase;
import com.mybank.notification.usecase.dto.SendNotificationOutput;
import com.mybank.notification.input.rest.doc.NotificationRestControllerDoc;
import com.mybank.notification.input.rest.dto.NotificationCreateRequestDTO;
import com.mybank.notification.input.rest.dto.NotificationCreateResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/notification")
public class NotificationRestController implements NotificationRestControllerDoc {

    private final SendNotificationUseCase sendNotificationUseCase;

    public NotificationRestController(SendNotificationUseCase sendNotificationUseCase) {
        this.sendNotificationUseCase = sendNotificationUseCase;
    }

    @PostMapping()
    @Override
    public NotificationCreateResponseDTO sendNotification(
            @RequestBody NotificationCreateRequestDTO request
    )  {
        NotificationType type = NotificationType.getNotificationType(request.type());
        SendNotificationOutput resultOutput = sendNotificationUseCase.send(type, request.userId(), request.message());
        return new NotificationCreateResponseDTO(resultOutput);
    }
}
