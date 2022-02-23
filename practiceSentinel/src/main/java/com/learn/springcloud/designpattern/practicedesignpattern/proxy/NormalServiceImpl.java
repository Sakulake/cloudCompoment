package com.learn.springcloud.designpattern.practicedesignpattern.proxy;

public class NormalServiceImpl implements NormalService {

    @Override
    public void sayHello() {
        System.out.println("normal 123456");
    }
}
//public class NormalServiceImpl  {
//
//    public void sayHello() {
//        System.out.println("normal 123456");
//    }
//}
