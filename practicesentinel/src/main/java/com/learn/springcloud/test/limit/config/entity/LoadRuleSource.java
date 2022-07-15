package com.learn.springcloud.test.limit.config.entity;

import com.learn.springcloud.test.limit.config.entity.LimitConfig;

/**
 * @author sdw
 * @date 2021/12/22 10:22:58
 * @description
 */

public interface LoadRuleSource {
    LimitConfig load(String path);
}
