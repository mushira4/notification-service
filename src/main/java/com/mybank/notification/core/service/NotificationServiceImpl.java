package com.mybank.notification.core.service;

import com.mybank.notification.core.domain.Notification;
import com.mybank.notification.core.exception.NotificationRateLimitException;
import com.mybank.notification.core.repository.RateLimitRepository;
import com.mybank.notification.core.domain.NotificationType;
import com.mybank.notification.core.service.dto.NotificationServiceOutput;
import com.mybank.notification.infra.gateway.Gateway;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final Gateway gateway;
    private final RateLimitRepository rateLimitRepository;

    public NotificationServiceImpl(Gateway gateway, RateLimitRepository rateLimitRepository) {
        this.gateway = gateway;
        this.rateLimitRepository = rateLimitRepository;
    }

    public NotificationServiceOutput send(NotificationType type, String userId, String message) {
        Notification notification = new Notification(type, userId, message);

        Integer numberOfEntries = rateLimitRepository.findNumberOfEntries(notification);
        if (notification.getRateLimitQuantity() > numberOfEntries) {
            rateLimitRepository.save(notification);
            gateway.send(notification); //TODO: Implement rollback mechanism
            return new NotificationServiceOutput(type, userId, message, numberOfEntries + 1);
        } else {
            System.out.println("Rate limit exceeded for " + type + " for user " + userId); // Kept printing this for easier understanding of the flow
            throw new NotificationRateLimitException(
                    "Rate limit exceeded for " + type + " for user " + userId + "." +
                    " Limit is " + notification.getRateLimitQuantity() + " and number of entries is " + numberOfEntries);
        }
    }
}

