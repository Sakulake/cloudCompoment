package com.learn.springcloud.javafunc;


import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.locks.ReentrantLock;

public class RxJavaTest {
    public static void main(String[] args) {
        Observable.just(getLightState())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        System.out.println("onError");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                        System.out.println("onNext");
                    }

                });
    }

    private static String getLightState() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "GG";
    }

}
