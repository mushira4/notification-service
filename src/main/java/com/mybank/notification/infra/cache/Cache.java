package com.mybank.notification.infra.cache;

public interface Cache {
    void addWithExpiration(String key, long timeToExpiryInSeconds);
    Integer findNumberOfEntries(String key);
}
