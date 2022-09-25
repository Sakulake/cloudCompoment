package com.learn.springcloud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread name "+Thread.currentThread().getName()+" thread id "+Thread.currentThread().getId()+" is running");
                lock.lock();
                try {
                    System.out.println("thread name "+Thread.currentThread().getName()+" thread id "+Thread.currentThread().getId()+" got lock");
                    TimeUnit.SECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("thread name "+Thread.currentThread().getName()+" thread id "+Thread.currentThread().getId()+" is exit");
                return;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread name "+Thread.currentThread().getName()+" thread id "+Thread.currentThread().getId()+" is running");
                lock.lock();

                try {
                    System.out.println("thread name "+Thread.currentThread().getName()+" thread id "+Thread.currentThread().getId()+" got lock");

                } finally {
                    lock.unlock();
                }
                System.out.println("thread name "+Thread.currentThread().getName()+" thread id "+Thread.currentThread().getId()+" is exit");
                return;
            }
        }).start();



    }
}
