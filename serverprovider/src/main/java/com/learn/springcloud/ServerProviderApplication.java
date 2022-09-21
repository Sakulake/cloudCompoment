package com.learn.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sdw
 * @version 1.0
 * @className ServerProviderApplication
 * @date 2021/11/19 14:41:13
 * @description
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@EnableScheduling  //启用后，会定时拉取配置
@EnableTransactionManagement
public class ServerProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerProviderApplication.class, args);
    }

}
