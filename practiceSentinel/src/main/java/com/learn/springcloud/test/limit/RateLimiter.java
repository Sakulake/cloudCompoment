package com.learn.springcloud.test.limit;

import com.learn.springcloud.test.limit.config.LimitConfig;
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
    private LimitConfig rule;

    public RateLimiter() {
        // 将限流规则配置文件ratelimiter-rule.yaml中的内容读取到RuleConfig中
        InputStream in = null;
        LimitConfig ruleConfig = null;
        try {
            in = this.getClass().getResourceAsStream("/limit_rule.yml");
            if (in != null) {
                Yaml yaml = new Yaml();
                ruleConfig = yaml.loadAs(in, LimitConfig.class);
            }
        } finally {
            if (in != null) {
                try { in.close();
                } catch (IOException e) {
                    log.error("close file error:{}", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
    }
}
