package com.learn.springcloud.javafunc.mulitthread;

import sun.nio.ch.ThreadPool;

import java.util.Queue;
import java.util.concurrent.*;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);
        semaphore.acquire();
        semaphore.release();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,
                1,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
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
                    System.out.println("执行第4步");
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

        Semaphore semaphore = new Semaphore(10);

        semaphore.acquire();
        semaphore.release();
        System.out.println("执行第3步");
        return;
    }

    public void getOne(){

    }

    public void getTwo(){

    }
}
