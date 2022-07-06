package com.learn.springcloud.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
    static volatile AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(integer){
                    integer.incrementAndGet();
                    System.out.println("this is thread1,integer is " +integer.intValue());
                    try {
                        System.out.println("Thread1 wait");
                        integer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread1 notified");
                    System.out.println("Thread1 notify other");
                    integer.notify();
                }

            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(integer){
                    integer.incrementAndGet();
                    System.out.println("this is thread2,integer is " +integer.intValue());
                    try {
                        System.out.println("Thread2 wait");
                        integer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread2 notified");

                    System.out.println("Thread2 notify other");
                    integer.notify();
                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(integer){
                    integer.incrementAndGet();
                    System.out.println("this is thread3,integer is " +integer.intValue());
                    try {
                        System.out.println("Thread3 wait");
                        integer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread3 notified");
                    System.out.println("Thread3 notify other");
                    integer.notify();
                }
            }


        });


        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(integer) {
                    integer.notify();
                }

            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("excuted，integer is " +integer.intValue());
    }
}
