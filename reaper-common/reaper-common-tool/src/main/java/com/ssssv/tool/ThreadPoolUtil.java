package com.ssssv.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolUtil {

    private ThreadPoolUtil() {
    }

    public static void shutdownPool(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout, TimeUnit timeUnit) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(shutdownTimeout, timeUnit)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(shutdownNowTimeout, timeUnit)) {
                    log.error("com.ssssv.tool.ThreadPoolUtils.shutdownPool.error");
                }
            }
        } catch (InterruptedException ie) {
            log.error("com.ssssv.tool.ThreadPoolUtils.shutdownPool.interrupted.error:{}", ie.getMessage(), ie);
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
