package com.mybank.notification.input.rest;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.core.service.NotificationService;
import com.mybank.notification.core.service.dto.NotificationServiceOutput;
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

    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping()
    @Override
    public NotificationCreateResponseDTO sendNotification(
            @RequestBody NotificationCreateRequestDTO request
    )  {
        NotificationType type = NotificationType.getNotificationType(request.type());
        NotificationServiceOutput resultOutput = notificationService.send(type, request.userId(), request.message());
        return new NotificationCreateResponseDTO(resultOutput);
    }
}
