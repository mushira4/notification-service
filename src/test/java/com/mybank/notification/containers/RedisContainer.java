package com.mybank.notification.containers;

import org.testcontainers.containers.GenericContainer;

public class RedisContainer extends GenericContainer<RedisContainer> {
    public RedisContainer() {
        super("redis:5.0.3-alpine");
        withExposedPorts(6379);
    }
}
