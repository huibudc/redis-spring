package com.huibudc.redisSpring.services;

import com.huibudc.redisSpring.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisServiceTest {
    @Autowired
    private RedisService redisService;

    @Test
    public void redisServiceTest() {
        User user = new User(2, "xiaoxiaoping", 16);
        redisService.set("user", user, 1000 * 60L);
        User userV1 = redisService.get("user", User.class);
        assertEquals(2, userV1.getId());
        assertEquals("xiaoxiaoping", userV1.getName());
        assertEquals(16, userV1.getAge());
    }
}