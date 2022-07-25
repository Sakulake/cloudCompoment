package com.learn.springcloud.test.limit.config;

import com.learn.springcloud.test.limit.config.entity.LimitConfig;

/**
 * @author sdw
 * @date 2021/12/22 10:29:57
 * @description
 */

public interface ParseFile {
    LimitConfig parse(String filePath);
}
