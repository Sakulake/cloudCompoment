package com.learn.springcloud.test.limit.config;

import com.learn.springcloud.test.limit.config.entity.LimitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sdw
 * @version 1.0
 * @className YamlParse
 * @date 2021/12/22 10:35:06
 * @description
 */

public class YamlParser implements ParseFile {

    private Logger log = LoggerFactory.getLogger(YamlParser.class);

    @Override
    public LimitConfig parse(String filePath) {
        // 将限流规则配置文件ratelimiter-rule.yaml中的内容读取到RuleConfig中
        InputStream in = null;
        LimitConfig rule= null;
        try {
            in = this.getClass().getResourceAsStream(filePath);
            if (in != null) {
                Yaml yaml = new Yaml();
                rule = yaml.loadAs(in, LimitConfig.class);

            }
        } finally {
            if (in != null) {
                try { in.close();
                } catch (IOException e) {
                    log.error("close file error:{}", e);
                }
            }
        }
        return rule;
    }
}
