package com.ssssv.tool;

import org.slf4j.Logger;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CompletableFutureUtil {

    /**
     * 获取future返回结果
     */
    public static <T> T getResult(Future<T> future, long timeout, TimeUnit timeUnit, T defaultValue, Logger logger) {
        try {
            return future.get(timeout, timeUnit);
        } catch (Exception e) {
            logger.error("CompletableFutureUtils.getResult.error:{},defaultValue:{}", e.getMessage(), e);
            logger.error("CompletableFutureUtils.getResult.error.returnDefaultValue:{}", defaultValue);
            return defaultValue;
        }
    }

}
