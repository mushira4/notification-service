package com.mybank.notification.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateLimitConfigFactoryTest {

    @Test
    void testNewsNotificationType() {
        RateLimitConfig rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(NotificationType.NEWS);

        assertEquals(1, rateLimitConfig.getQuantity());
        assertEquals(24 * 60 * 60, rateLimitConfig.getTimeInMillis());
    }

    @Test
    void testMarketingNotificationType() {
        RateLimitConfig rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(NotificationType.MARKETING);

        assertEquals(3, rateLimitConfig.getQuantity());
        assertEquals(3 * 60 * 60, rateLimitConfig.getTimeInMillis());
    }

    @Test
    void testStatusNotificationType() {
        RateLimitConfig rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(NotificationType.STATUS);

        assertEquals(2, rateLimitConfig.getQuantity());
        assertEquals(2 * 60, rateLimitConfig.getTimeInMillis());
    }

    @Test
    void testOtherNotificationType() {
        RateLimitConfig rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(NotificationType.ETC);

        assertEquals(10, rateLimitConfig.getQuantity());
        assertEquals(60, rateLimitConfig.getTimeInMillis());
    }
}
