package com.ssssv.user.controller;

import com.ssssv.redis.util.RedisShareLockUtil;
import com.ssssv.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisShareLockUtil redisShareLockUtil;

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/redis")
    public void testRedis() {
        redisUtil.set("name", "ssssv");
    }

    @GetMapping("/redislock")
    public void testRedisLock() {
        redisShareLockUtil.lock("ssssv", "7777", 100000L);
    }

    @GetMapping("/redisunlock")
    public void testRedisUnlock() {
        redisShareLockUtil.unlock("ssssv", "7777");
    }

    @GetMapping("/testlog")
    public void testLog() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            log.info("Log {}", i);
        }
        long endTime = System.currentTimeMillis();
        log.info("Use Time: {}", endTime - startTime);
    }


}
