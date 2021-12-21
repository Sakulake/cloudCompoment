package com.learn.springcloud.test.limit.config;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;

public class LimitConfig {
    private List<AppLimitRule> configs;

    public List<AppLimitRule> getConfigs() {
        return configs;
    }

    public void setConfigs(List<AppLimitRule> configs) {
        this.configs = configs;
    }
}
