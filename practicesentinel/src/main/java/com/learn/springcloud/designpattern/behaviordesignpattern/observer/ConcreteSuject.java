package com.learn.springcloud.designpattern.behaviordesignpattern.observer;

import java.util.ArrayList;
import java.util.List;

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
