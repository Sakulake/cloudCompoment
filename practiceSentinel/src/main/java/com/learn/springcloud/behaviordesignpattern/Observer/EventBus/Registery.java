package com.learn.springcloud.behaviordesignpattern.Observer.EventBus;

import java.lang.reflect.Method;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Registery {
    private ConcurrentHashMap<Class<?>,CopyOnWriteArrayList<ObserverAction>> observers = new ConcurrentHashMap<>();

    public void register(Observer observer) {
        Method[]  methods = observer.getClass().getDeclaredMethods();
        for (Method method : methods){
            if(method.isAnnotationPresent(Subsribe.class)){
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> msgId = parameterTypes[0];
                ObserverAction  action = new ObserverAction(observer,method);
                if(observers.get(msgId)!= null){
                    observers.get(msgId).add(action);
                }else{
                    CopyOnWriteArrayList<ObserverAction> actions= new CopyOnWriteArrayList<>();
                    actions.add(action);
                    observers.put(msgId,actions);
                }

            }
        }
    }

    public CopyOnWriteArrayList<ObserverAction> getActions(String msgId) {
        return observers.get(msgId);
    }
}
