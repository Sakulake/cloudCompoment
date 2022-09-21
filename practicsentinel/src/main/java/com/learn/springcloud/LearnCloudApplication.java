package com.learn.springcloud;

import com.learn.springcloud.config.StudentConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author sdw
 * @date 2021/11/18 10:29:42
 * @description
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling  //启用后，会定时拉取配置,实际初始化一个线程池
@EnableAspectJAutoProxy
@EnableAsync
@EnableConfigurationProperties({StudentConfig.class})
public class LearnCloudApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnCloudApplication.class, args);
    }
}
