package com.ssssv.user.controller;

import com.ssssv.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/redis")
    public void testRedis() {
        redisUtil.set("name", "ssssv");
    }
}
