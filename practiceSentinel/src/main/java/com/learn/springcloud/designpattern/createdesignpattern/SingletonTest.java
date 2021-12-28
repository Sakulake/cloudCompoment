package com.learn.springcloud.designpattern.createdesignpattern;

public class SingletonTest {
    public static volatile SingletonTest instance = null;

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

}
