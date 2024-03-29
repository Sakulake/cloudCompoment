package com.learn.springcloud;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ThreadTest {
    private static final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    private static final ReentrantLock reentrantLockTest = new ReentrantLock();

    static Semaphore  semaphore = new Semaphore(3);
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
    public static void main(String[] args) {        //基于println方法中的synchronize代码块测试运行或者监视线程
        Integer integer = 1000;
        Integer integer1 = 1000;
        Future<Integer> future = new CompletableFuture<>();
        if (integer1 == integer){
            System.out.println("我错了");
        }else{
            System.out.println("我的对");
        }
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();
        readLock.lock();
        writeLock.lock();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            semaphore.release();
        }
        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println("运行或者监视线程1");
            }
        }, "运行或者监视线程1");
        thread1.start();        //基于println方法中的synchronize代码块测试运行或者监视线程
        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("运行或者监视线程2");
            }
        }, "运行或者监视线程2");
        thread2.start();        //monitor对象等待线程
        Object lock = new Object();
        Thread thread3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "等待线程synchronized");
        thread3.start();        //reentrantLock中的条件对象调用await方法线程为驻留线程
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        Thread thread4 = new Thread(() -> {
            reentrantLock.lock();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }, "等待线程reentrantLock");
        thread4.start();        //休眠线程
        Thread thread5 = new Thread(() -> {
            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "休眠线程");
        thread5.start();
        Thread thread6 = new Thread(ThreadTest::lockMethod, "reentrantLock运行线程");
        thread6.start();        //等待获取reentrantLock的线程为驻留线程
        Thread thread7 = new Thread(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lockMethod();
        }, "reentrantLock监视线程");
        thread7.start();        //线程池中的空闲线程为驻留线程
        singleThreadExecutor.execute(() -> {            //线程池中的线程是懒加载，需要运行任务之后才会产生线程
            System.out.println("驻留线程运行");
        });
    }

    private static void lockMethod() {
        reentrantLockTest.lock();
        try {
            while (true) {

            }
        } finally {
            reentrantLockTest.unlock();
        }
    }    //println源码也简单贴一下
    //java.io.PrintStream#println(java.lang.String)
//    public void println(String x) {        //this表示System.out这个PrintStream对象
//        synchronized (this) {
//            print(x);
//            newLine();
//        }
//    }newLine
}
