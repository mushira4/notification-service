package com.mybank.notification.infra.cache;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisCache implements Cache {

    private final Jedis jedis;

    public RedisCache() {
        this.jedis = new Jedis();;
    }

    public RedisCache(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void addWithExpiration(String key, long timeToExpiry) {
        String cachedValue = jedis.get(key);
        if (cachedValue != null){
            int value = Integer.parseInt(cachedValue);
            long timeToLive = jedis.ttl(key);
            if (timeToLive > 0) {
                jedis.set(key, String.valueOf(value + 1));
                jedis.expire(key, timeToLive);
            } else {  // Key has no expiration time, it was already expired in between (CORNER CASE)
                jedis.set(key, "1");
                jedis.expire(key, timeToExpiry);
            }
        } else {
            jedis.set(key, "1");
            jedis.expire(key, timeToExpiry);
        }
    }

    @Override
    public Integer findNumberOfEntries(String key) {
        String s = jedis.get(key);
        if(s == null) {
            return 0;
        }
        return Integer.parseInt(s);
    }

}
