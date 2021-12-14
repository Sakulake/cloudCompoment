package com.learn.springcloud.behaviordesignpattern.observer.EventBus;

import org.apache.catalina.Executor;

public class AsyncEventBus extends EventBus{
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}