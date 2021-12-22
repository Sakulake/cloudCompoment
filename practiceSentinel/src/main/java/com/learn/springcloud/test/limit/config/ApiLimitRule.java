package com.learn.springcloud.test.limit.config;


/**
 * @Author sdw
 * @Description 命名注意大小写
 * @Date  2021/12/22 9:40 上午
 * @Param  * @param null
 * @return
 **/
public class ApiLimitRule {
    private String api;
    private Integer limit;
    private Integer unit;

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
