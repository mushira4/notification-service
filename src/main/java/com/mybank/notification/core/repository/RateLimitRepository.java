package com.mybank.notification.core.repository;

import com.mybank.notification.core.domain.Notification;
import com.mybank.notification.infra.cache.Cache;
import org.springframework.stereotype.Repository;

@Repository
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
