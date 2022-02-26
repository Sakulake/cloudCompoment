package com.learn.springcloud.javafunc.mulitthread;

import sun.nio.ch.ThreadPool;

import java.util.Queue;
import java.util.concurrent.*;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
        new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println(r.toString()+"执行了拒绝策略");
                    }
                }
        );

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("结束关闭，线程池");
                threadPool.shutdown();
            }
        }));

        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("执行第1步");
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    System.out.println("执行第2步");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();

        System.out.println("执行第3步");
        return;
    }

    public void getOne(){

    }

    public void getTwo(){

    }
}