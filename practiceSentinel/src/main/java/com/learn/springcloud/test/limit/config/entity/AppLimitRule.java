package com.learn.springcloud.test.limit.config.entity;

import java.util.List;

public class AppLimitRule {
    private String appId;
    private List<ApiLimitRule> limits ;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public List<ApiLimitRule> getLimits() {
        return limits;
    }

    public void setLimits(List<ApiLimitRule> limits) {
        this.limits = limits;
    }
}
