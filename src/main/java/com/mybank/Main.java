package com.mybank;

import com.mybank.input.console.NotificationInputConsole;
import com.mybank.infra.cache.Cache;
import com.mybank.infra.cache.RedisCache;
import com.mybank.infra.gateway.Gateway;
import com.mybank.core.repository.RateLimitRepository;
import com.mybank.core.service.NotificationService;
import com.mybank.core.service.NotificationServiceImpl;

public class Main {
    public static void main(String[] args) {
        Cache cache = new RedisCache();
        RateLimitRepository rateLimitRepository = new RateLimitRepository(cache);

        Gateway gateway = new Gateway();

        NotificationService service = new NotificationServiceImpl(gateway, rateLimitRepository);
        NotificationInputConsole console = new NotificationInputConsole(service);

        console.initConsole();
    }
}
