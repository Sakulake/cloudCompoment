package com.learn.springcloud.test.limit.config;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

public class AppLimitRule {
    private String appId;
    private List<ApiLimitRule> limits ;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<ApiLimitRule> getRules() {
        return limits;
    }

    public void setRules(List<ApiLimitRule> rules) {
        this.limits = rules;
    }
}
