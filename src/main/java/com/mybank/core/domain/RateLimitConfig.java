package com.mybank.core.domain;

public class RateLimitConfig {
    private final Integer quantity;
    private final long timeInSeconds;

    public RateLimitConfig(Integer quantity, long timeInSeconds) {
        this.quantity = quantity;
        this.timeInSeconds = timeInSeconds;
    }

    public long getTimeInMillis() {
        return timeInSeconds;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
