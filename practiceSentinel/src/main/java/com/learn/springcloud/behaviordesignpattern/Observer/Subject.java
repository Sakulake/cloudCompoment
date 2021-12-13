package com.learn.springcloud.behaviordesignpattern.Observer;

public interface Subject {
    public void registe(Observer observer);
    public void move(Observer observer);
    public void notifySucc ();
}
