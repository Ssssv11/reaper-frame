package com.ssssv.user.controller;

import com.ssssv.redis.util.RedisShareLockUtil;
import com.ssssv.redis.util.RedisUtil;
import com.ssssv.tool.ExportWordUtil;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/testexport")
    public void testExport() throws TemplateException, IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Ssssv");
        map.put("auditName", "Ninn");
        ExportWordUtil.exportWord(map, "导出文件", "wordExport.ftl");
    }


}
