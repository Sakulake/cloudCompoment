package com.learn.springcloud.designpattern.behaviordesignpattern.observer;

public interface Subject {
    public void registe(Observer observer);
    public void move(Observer observer);
    public void notifySucc ();
}
