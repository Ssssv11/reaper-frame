package com.ssssv.user.cache;

import com.ssssv.redis.init.AbstractCache;
import com.ssssv.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysUserCache extends AbstractCache {

    private static final String SYS_USER_CACHE_KEY = "SYS_USER";

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void initCache() {
        redisUtil.set("age", "22");
    }

    @Override
    public <T> T getCache(String key) {
        if(!redisUtil.hasKey(key)) {
            reloadCache();
        }
            return (T) redisUtil.get(key);
    }

    @Override
    public void clearCache() {
        redisUtil.del(SYS_USER_CACHE_KEY);
    }
}
