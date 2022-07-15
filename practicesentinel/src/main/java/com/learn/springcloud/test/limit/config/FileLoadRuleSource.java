package com.learn.springcloud.test.limit.config;

import com.learn.springcloud.test.limit.config.entity.LoadRuleSource;
import com.learn.springcloud.test.limit.config.entity.LimitConfig;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sdw
 * @version 1.0
 * @className FileLoadRuleSource
 * @date 2021/12/22 10:26:21
 * @description
 */

public class FileLoadRuleSource implements LoadRuleSource {
    private Logger log = LoggerFactory.getLogger(FileLoadRuleSource.class);

    ConcurrentHashMap<String,ParseFile> parses = new ConcurrentHashMap<>();

    {
        // TODO: 2021/12/22  init parses
    }
    @Override
    public LimitConfig load(String filePath) {
        String type = filePath.split(".")[1];
        return  parses.get(type).parse(filePath);

    }
}
