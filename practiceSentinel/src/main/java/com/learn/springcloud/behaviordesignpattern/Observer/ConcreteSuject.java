package com.learn.springcloud.behaviordesignpattern.Observer;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ConcreteSuject implements Subject{

    List<Observer> observers = new ArrayList<>();


    @Override
    public void registe(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void move(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySucc() {
        for (Observer observer : observers){
            observer.handle(null);
        }
    }

}
