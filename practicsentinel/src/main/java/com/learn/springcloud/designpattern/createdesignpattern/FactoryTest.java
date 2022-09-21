package com.learn.springcloud.designpattern.createdesignpattern;

import java.util.HashMap;

// 工厂的工厂
// 工厂就是创建bean的类
public class FactoryTest {
    private HashMap factoryOfFactory = new HashMap();
    // add factoryOfFactory
    //....

    public Object getFactory(String id){
        return factoryOfFactory.get(id);
    }

}
