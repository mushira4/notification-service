package com.mybank.notification.core.exception;

public class NotificationRateLimitException extends IllegalStateException {
    public NotificationRateLimitException(String message) {
        super(message);
    }
}
