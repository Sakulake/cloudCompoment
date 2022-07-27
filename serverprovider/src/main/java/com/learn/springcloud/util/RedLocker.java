package com.learn.springcloud.util;

import com.learn.springcloud.config.RedLockUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class RedLocker implements DistributeLocker {



    @Autowired
    RedissonClient redissonClient;



    @Override
    public boolean tryLock(String lockName, int timeout) throws InterruptedException {

        RLock rLock = redissonClient.getLock(lockName);
        return rLock.tryLock(1,timeout, TimeUnit.SECONDS);

    }

    public void unLock(String lockName)  {
        RLock rLock = redissonClient.getLock(lockName);
        rLock.unlock();
    }

}
