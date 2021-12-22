package com.learn.springcloud.test.limit;

import com.learn.springcloud.test.limit.config.entity.LimitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);
    // 为每个api在内存中存储限流计数器
    private ConcurrentHashMap counters = new ConcurrentHashMap<>();
    private LimiterFactory limiterFactory;
    private LimitConfig rule;
    public RateLimiter() {

    }

    public boolean rateLimit(String appId,String url){
        Limiter limiter = limiterFactory.getLimiter();
        return limiter.limit(appId,url);
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
    }
}
