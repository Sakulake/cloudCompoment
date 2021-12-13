package com.learn.springcloud.behaviordesignpattern.Observer.EventBus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventBus {

    private Executor executor;

    public EventBus() {
        //直接执行
        this.executor = null;
    }


    public EventBus(Executor executor ) {
        this.executor = executor;
    }
    private Registery registery;

    public void register(Observer observer){
        registery.register(observer);
    }

    public void post(String msgId){
        CopyOnWriteArrayList<ObserverAction> actions = registery.getActions(msgId);
        for (ObserverAction action : actions){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    action.execute();
                }
            });

        }
    }
}
