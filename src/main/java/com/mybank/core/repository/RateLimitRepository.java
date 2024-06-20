package com.mybank.core.repository;

import com.mybank.core.domain.Notification;
import com.mybank.infra.cache.Cache;

public class RateLimitRepository {
    private final Cache cache;

    public RateLimitRepository(Cache cache) {
        this.cache = cache;
    }

    public void save(Notification notification) {
        cache.addWithExpiration(notification.getRateLimitKey(), notification.getRateLimitTimeToLive());
    }

    public Integer findNumberOfEntries(Notification notification) {
        return cache.findNumberOfEntries(notification.getRateLimitKey());
    }
}
