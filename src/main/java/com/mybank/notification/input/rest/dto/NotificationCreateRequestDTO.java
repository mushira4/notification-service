package com.mybank.notification.input.rest.dto;

public class NotificationCreateRequestDTO {
    private String type;
    private String userId;
    private String message;

    public NotificationCreateRequestDTO() {
    }

    public NotificationCreateRequestDTO(String type, String userId, String message) {
        this.type = type;
        this.userId = userId;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "NotificationCreateRequestDTO{" +
                "type='" + type + '\'' +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
