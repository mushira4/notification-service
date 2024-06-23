package com.mybank.notification.input.rest;

import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.core.service.NotificationService;
import com.mybank.notification.input.rest.dto.NotificationCreateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/notification")
public class NotificationRestController {

    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping()
    public void sendNotification(
            @RequestBody NotificationCreateRequestDTO request
    ) {
        NotificationType type = NotificationType.getNotificationType(request.getType());
        notificationService.send(type, request.getUserId(), request.getMessage());
    }

}
