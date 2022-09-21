package com.learn.springcloud.util;

public interface DistributeLocker {
    boolean tryLock(String lockName , int timeout) throws InterruptedException;
    void unLock(String lockName);
}
