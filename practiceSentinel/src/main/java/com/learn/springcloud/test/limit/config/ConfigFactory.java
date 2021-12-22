package com.learn.springcloud.test.limit.config;

import com.learn.springcloud.test.limit.config.entity.LoadRuleSource;
import com.learn.springcloud.test.limit.config.entity.LimitConfig;

import java.util.HashMap;

/**
 * @author sdw
 * @version 1.0
 * @className ConfigFactory
 * @date 2021/12/22 10:18:05
 * @description 解析获取限流规则
 * 1 从文件获取
 * 2 从数据库获取
 */

public class ConfigFactory {
    private static String path;
    private  static HashMap<String, LoadRuleSource> loadRuleSources = new HashMap<>();

    {
        // TODO: 2021/12/22 init  loadRuleSource
    }
    //filePath || dateSource

    public static LimitConfig getLimitConfig(String type){
        return loadRuleSources.get(type).load(path);
    }
}
