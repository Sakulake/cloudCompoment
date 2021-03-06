package com.learn.springcloud.designpattern.behaviordesignpattern.observer;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executors;

public class AsyncConcreteSubject implements Subject{
    EventBus eventBus = new EventBus();
    EventBus asyncEeventBus = new AsyncEventBus(Executors.newFixedThreadPool(20));

    @Override
    public void registe(Observer observer) {
        eventBus.register(observer);
    }

    @Override
    public void move(Observer observer) {
        eventBus.unregister(observer);
    }

    @Override
    public void notifySucc() {
        eventBus.post(null);
    }
}
