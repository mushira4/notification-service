package com.mybank.core.domain;

public class Notification {
    private final NotificationType type;
    private final String userId;
    private final String message;
    private final RateLimitConfig rateLimitConfig;

    public Notification(NotificationType type, String userId, String message) {
        this.type = type;
        this.userId = userId;
        this.message = message;
        this.rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(type);
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }

    public String getRateLimitKey() {
        return type.getDescription() + "_" + userId;
    }

    public Integer getRateLimitQuantity() {
        return rateLimitConfig.getQuantity();
    }

    public long getRateLimitTimeToLive() {
        return rateLimitConfig.getTimeInMillis();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Notification that = (Notification) obj;
        return userId.equals(that.userId) && message.equals(that.message) && type == that.type;
    }
}
