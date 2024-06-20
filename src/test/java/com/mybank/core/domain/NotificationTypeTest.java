package com.mybank.core.domain;

import com.mybank.core.domain.NotificationType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTypeTest {

    @Test
    void getValues() {
        assertEquals(NotificationType.STATUS, NotificationType.getNotificationType("status"));
        assertEquals(NotificationType.NEWS, NotificationType.getNotificationType("news"));
        assertEquals(NotificationType.MARKETING, NotificationType.getNotificationType("marketing"));
        assertEquals(NotificationType.ETC, NotificationType.getNotificationType("x"));
    }

}
