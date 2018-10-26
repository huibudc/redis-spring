package com.huibudc.redisSpring.services;

import com.huibudc.redisSpring.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final long WEEK_SECONDS = 7 * 24 * 60 * 60;

    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, JsonUtil.convertObj2String(val));
    }

    public void set(String key, Object val, long expireTime) {
        redisTemplate.opsForValue().set(key, JsonUtil.convertObj2String(val), expireTime, TimeUnit.SECONDS);
    }

    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    public <T> T get(String key, Class<T> clazz) {
        String s = get(key);
        if (s == null) {
            return null;
        }
        return JsonUtil.convertString2Obj(s, clazz);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
