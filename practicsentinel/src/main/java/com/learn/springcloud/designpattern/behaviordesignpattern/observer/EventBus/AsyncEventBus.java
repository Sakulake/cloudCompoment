package com.learn.springcloud.designpattern.behaviordesignpattern.observer.EventBus;

import org.apache.catalina.Executor;

public class AsyncEventBus extends EventBus{
    public AsyncEventBus(Executor executor) {
        super(executor);
    }
}
