package com.mybank.core.service;

import com.mybank.core.domain.Notification;
import com.mybank.core.repository.RateLimitRepository;
import com.mybank.core.domain.NotificationType;
import com.mybank.infra.gateway.Gateway;

public class NotificationServiceImpl implements NotificationService {
    private final Gateway gateway;
    private final RateLimitRepository rateLimitRepository;

    public NotificationServiceImpl(Gateway gateway, RateLimitRepository rateLimitRepository) {
        this.gateway = gateway;
        this.rateLimitRepository = rateLimitRepository;
    }

    public void send(NotificationType type, String userId, String message) {
        Notification notification = new Notification(type, userId, message);

        if (notification.getRateLimitQuantity() > rateLimitRepository.findNumberOfEntries(notification)) {
            rateLimitRepository.save(notification);
            gateway.send(notification); //TODO: Implement rollback mechanism
        } else {
            //TODO: Should be considered an exception ???
            System.out.println("Rate limit exceeded for " + type + " for user " + userId); // Kept printing this for easier understanding of the flow
        }
    }
}

