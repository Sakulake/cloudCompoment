package com.learn.springcloud.test.limit;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sdw
 * @version 1.0
 * @className LimiterFactory
 * @date 2021/12/21 17:44:48
 * @description
 */

public class LimiterFactory {
    private ConcurrentHashMap <String,Limiter> limiters = new ConcurrentHashMap<>();


    public Limiter getLimiter(String type){
        return limiters.get(type);
    }
}
