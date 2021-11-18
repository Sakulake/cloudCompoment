package com.learn.springcloud;

import com.learn.springcloud.config.StudentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sdw
 * @date 2021/11/18 10:29:42
 * @description
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableScheduling  //启用后，会定时拉取配置
@EnableConfigurationProperties({StudentConfig.class})
public class LearnCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnCloudApplication.class, args);
    }
}
