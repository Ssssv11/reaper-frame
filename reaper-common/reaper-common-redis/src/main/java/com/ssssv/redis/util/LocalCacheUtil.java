package com.ssssv.redis.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
@Slf4j
public class LocalCacheUtil<K, V> {

    @Value("${guava.cache.switch}")
    public Boolean switchCache;

    private Cache<String, String> localCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();

    public Map<K, V> getResult(List<K> list, String cachePrefix,
                               Class<V> clazz, Function<List<K>, Map<K, V>> function) {
        if (list == null) return Collections.emptyMap();

        Map<K, V> resultMap = new HashMap<>(16);
        if (switchCache) {
            resultMap = function.apply(list);
            return resultMap;
        }

        List<K> noCacheList = new ArrayList<>();
        for (K k : list) {
            String cacheKey = cachePrefix + "_" + k;
            String content = localCache.getIfPresent(cacheKey);

            if(StringUtils.isNotBlank(content)) {
                V v = JSON.parseObject(content, clazz);
                resultMap.put(k, v);
            } else {
                noCacheList.add(k);
            }
        }

        if(CollectionUtils.isEmpty(noCacheList)) {
            return resultMap;
        }

        Map<K, V> noCacheResultMap = function.apply(noCacheList);
        if(noCacheResultMap == null || noCacheResultMap.isEmpty()) {
            return resultMap;
        }

        for (Map.Entry<K, V> entry : noCacheResultMap.entrySet()) {
            K k = entry.getKey();
            V v = entry.getValue();
            resultMap.put(k, v);

            String cacheKey = cachePrefix + "_" + k;
            localCache.put(cacheKey, JSON.toJSONString(v));
        }

        return resultMap;
    }
}
