package com.mybank.core.domain;

public class RateLimitConfigFactory {
    public static RateLimitConfig getRateLimitConfig(NotificationType type) {
        return switch (type) {
            case STATUS -> new RateLimitConfig(2, 2 * 60);
            case NEWS -> new RateLimitConfig(1, 24 * 60 * 60);
            case MARKETING -> new RateLimitConfig(3, 3 * 60 * 60);
            default -> new RateLimitConfig(10, 60);
        };
    }
}
