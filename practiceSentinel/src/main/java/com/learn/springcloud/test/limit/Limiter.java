package com.learn.springcloud.test.limit;

/**
 * @author sdw
 * @date 2021/12/21 17:43:25
 * @description
 */

public interface Limiter {
    boolean limit(String podId,String url);
}
