package com.learn.springcloud.designpattern.createdesignpattern;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonTest {
    public static volatile SingletonTest instance = null;
    private SingletonTest (){

    }
    public static SingletonTest getInstance(){
        if (instance==null){
            synchronized (SingletonTest.class){
                if (instance==null){
                    instance = new SingletonTest();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        try {
//            ConcurrentHashMap
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
