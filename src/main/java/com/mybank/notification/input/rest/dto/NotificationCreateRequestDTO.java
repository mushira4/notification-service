package com.mybank.notification.input.rest.dto;

public record NotificationCreateRequestDTO(String type, String userId, String message) { }
