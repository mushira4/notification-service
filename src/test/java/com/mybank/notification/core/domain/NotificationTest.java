package com.mybank.notification.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    void testGetKey() {
        Notification notification = new Notification(NotificationType.STATUS, "user1", "message1");
        assertEquals("status_user1", notification.getRateLimitKey());
    }

    @Test
    void testRateLimitQuantity() {
        RateLimitConfig rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(NotificationType.ETC);

        Notification notification = new Notification(NotificationType.ETC, "user1", "");
        assertEquals(rateLimitConfig.getQuantity(), notification.getRateLimitQuantity());
    }

    @Test
    void testRateLimitTimeToLive() {
        RateLimitConfig rateLimitConfig = RateLimitConfigFactory.getRateLimitConfig(NotificationType.ETC);

        Notification notification = new Notification(NotificationType.ETC, "user1", "");
        assertEquals(rateLimitConfig.getTimeInMillis(), notification.getRateLimitTimeToLive());
    }

}
