package com.learn.springcloud.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RedLockUtil {
    RedissonClient redissonClient;


    @PostConstruct
    public  void init(){
        Config config = new Config();
//        config.useClusterServers().addNodeAddress("47.111.17.96:16379").setPassword("shaodawei2022");
        config.useSingleServer().setAddress("redis://47.111.17.96:16379").setPassword("shaodawei2022");
        redissonClient = Redisson.create(config);
    }

    @Bean
    public RedissonClient redissonClient(){
        return redissonClient;
    }
}
