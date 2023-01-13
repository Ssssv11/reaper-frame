package com.ssssv.redis.init;

import com.ssssv.redis.util.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class InitCache implements CommandLineRunner {

    @Override
    public void run(String... args) {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Map<String, AbstractCache> beanMap = applicationContext.getBeansOfType(AbstractCache.class);
        if (beanMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, AbstractCache> entry : beanMap.entrySet()) {
            AbstractCache abstractCache = (AbstractCache) SpringContextUtil.getBean(entry.getValue().getClass());
            abstractCache.initCache();
        }
    }

}
