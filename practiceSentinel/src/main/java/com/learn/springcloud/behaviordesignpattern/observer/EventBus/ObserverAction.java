package com.learn.springcloud.behaviordesignpattern.observer.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObserverAction {
    private Method method;
    private Object observer;

    public ObserverAction(Object observer,Method method) {
        this.observer = observer;
        this.method.setAccessible(true);
    }

    public void execute(){
        try {
            method.invoke(observer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
