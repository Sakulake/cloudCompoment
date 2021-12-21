package com.learn.springcloud.test.limit.config;

public class ApiLimitRule {
    private String api;
    private Integer limit;
    private Integer unit;

    public String getUrl() {
        return api;
    }

    public void setUrl(String url) {
        this.api = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }
}
