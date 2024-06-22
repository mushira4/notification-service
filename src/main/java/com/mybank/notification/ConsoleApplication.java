package com.mybank.notification;

import com.mybank.notification.input.console.NotificationConsoleController;
import com.mybank.notification.infra.cache.Cache;
import com.mybank.notification.infra.cache.RedisCache;
import com.mybank.notification.infra.gateway.Gateway;
import com.mybank.notification.core.repository.RateLimitRepository;
import com.mybank.notification.core.service.NotificationService;
import com.mybank.notification.core.service.NotificationServiceImpl;

public class ConsoleApplication {
    public static void main(String[] args) {
        Cache cache = new RedisCache();
        RateLimitRepository rateLimitRepository = new RateLimitRepository(cache);

        Gateway gateway = new Gateway();

        NotificationService service = new NotificationServiceImpl(gateway, rateLimitRepository);
        NotificationConsoleController console = new NotificationConsoleController(service);

        console.initConsole();
    }
}
