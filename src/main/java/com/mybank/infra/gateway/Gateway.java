package com.mybank.infra.gateway;

import com.mybank.core.domain.Notification;

public class Gateway {
    public void send(Notification notification) {
        System.out.println(
                "sending message to user " +
                        notification.getUserId() +
                        " with message: " +
                        notification.getMessage() + " of type " +
                        notification.getType().getDescription()); // Kept printing this for easier understanding of the flow
    }
}
