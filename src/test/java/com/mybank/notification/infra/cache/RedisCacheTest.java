package com.mybank.notification.infra.cache;

import com.mybank.notification.containers.RedisContainer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
class RedisCacheTest {

    @Container
    private static final RedisContainer REDIS_CONTAINER = new RedisContainer();

    private static RedisCache redisCache;

    private static Jedis jedis;

    @BeforeAll
    static void beforeAll() {
        String host = REDIS_CONTAINER.getContainerIpAddress();
        Integer port = REDIS_CONTAINER.getMappedPort(6379);

        jedis = new Jedis(host, port);
    }

    @BeforeEach
    void setUp() {
        jedis.flushAll();
        redisCache = new RedisCache(jedis);
    }

    @Test
    void testAddWithExpiration() {
        String key = "key";
        long timeToExpiryInSeconds = 60;

        redisCache.addWithExpiration(key, timeToExpiryInSeconds);
        Integer numberOfEntries = redisCache.findNumberOfEntries(key);
        assertEquals(1, numberOfEntries);
    }

    @Test
    void testAddWithExpirationWhenItExpires() {
        String key = "key";
        long timeToExpiryInSeconds = 1;

        redisCache.addWithExpiration(key, timeToExpiryInSeconds);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer numberOfEntries = redisCache.findNumberOfEntries(key);
        assertEquals(0, numberOfEntries);
    }

    @Test
    void testMultipleAddWithExpiration() {
        String key = "key";
        long timeToExpiryInSeconds = 60;

        redisCache.addWithExpiration(key, timeToExpiryInSeconds);
        redisCache.addWithExpiration(key, 1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer numberOfEntries = redisCache.findNumberOfEntries(key);
        assertEquals(2, numberOfEntries);
    }

    @Test
    void testMultipleAddWithExpirationWhenItExpires() {
        String key = "key";
        long timeToExpiryInSeconds = 2;

        redisCache.addWithExpiration(key, timeToExpiryInSeconds);
        redisCache.addWithExpiration(key, 1);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer numberOfEntries = redisCache.findNumberOfEntries(key);
        assertEquals(0, numberOfEntries);
    }

    @Test
    void testMultipleAddWithExpirationWhenItExpires2() {
        String key = "key";
        long timeToExpiryInSeconds = 1;

        redisCache.addWithExpiration(key, timeToExpiryInSeconds);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redisCache.addWithExpiration(key, 2);

        Integer numberOfEntries = redisCache.findNumberOfEntries(key);
        assertEquals(1, numberOfEntries);
    }

    @Test
    void testMultipleAddWithExpirationWhenItExpires3() {
        String key = "key";
        long timeToExpiryInSeconds = 1;

        redisCache.addWithExpiration(key, timeToExpiryInSeconds);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        redisCache.addWithExpiration(key, 2);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer numberOfEntries = redisCache.findNumberOfEntries(key);
        assertEquals(0, numberOfEntries);
    }

}
