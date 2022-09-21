package com.learn.springcloud.test.limit.config.entity;

import java.util.List;

public class LimitConfig {
    private List<AppLimitRule> configs;

    public List<AppLimitRule> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppLimitRule> configs) {
        this.configs = configs;
    }
}
