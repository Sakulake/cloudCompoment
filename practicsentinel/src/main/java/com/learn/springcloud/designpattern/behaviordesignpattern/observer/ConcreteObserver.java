package com.learn.springcloud.designpattern.behaviordesignpattern.observer;

import com.google.common.eventbus.Subscribe;

public class ConcreteObserver implements  Observer{
    @Subscribe
    @Override
    public void handle(Object o) {
        // TODO: 2021/12/13 todosomething 
    }
}
