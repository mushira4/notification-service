package com.mybank.notification.core.domain;

public enum NotificationType {

    STATUS("status"), NEWS("news"), MARKETING("marketing"), ETC("etc");

    private final String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean equals(String type) {
        if(description == null) {
            return false;
        }
        return this.description.equalsIgnoreCase(type);
    }

    public static NotificationType getNotificationType(String type) {
        for (NotificationType notificationType : NotificationType.values()) {
            if (notificationType.equals(type)) {
                return notificationType;
            }
        }
        return ETC;
    }
}
