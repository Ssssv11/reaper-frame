package com.ssssv.redis.util;

import com.ssssv.redis.exception.ShareLockException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisShareLockUtil {

    @Resource
    private RedisUtil redisUtil;

    private final Long TIME_OUT = 10000L;

    public boolean lock(String lockKey, String requestId, Long time) {
        if (StringUtils.isEmpty(lockKey) || StringUtils.isEmpty(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-加锁-参数异常");
        }
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + TIME_OUT;
        boolean res = false;
        while (currentTime < expireTime) {
            res = redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);
            if (res) {
                return res;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            currentTime = System.currentTimeMillis();
        }
        return res;
    }

    public boolean unlock(String key, String requestId) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(requestId)) {
            throw new ShareLockException("分布式锁-解锁-参数异常");
        }
        try {
            String value = redisUtil.get(key);
            if (requestId.equals(value)) {
                boolean del = redisUtil.del(key);
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean tryLock(String lockKey, String requestId, Long time) {
        if (StringUtils.isEmpty(lockKey) || StringUtils.isEmpty(requestId) || time <= 0) {
            throw new ShareLockException("分布式锁-尝试加速-参数异常");
        }
        return redisUtil.setNx(lockKey, requestId, time, TimeUnit.MILLISECONDS);
    }

}
