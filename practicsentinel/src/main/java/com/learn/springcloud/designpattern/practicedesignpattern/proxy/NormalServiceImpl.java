package com.learn.springcloud.designpattern.practicedesignpattern.proxy;

import com.learn.springcloud.javafunc.aop.MyAnnotation;
import org.springframework.stereotype.Service;


@Service
@MyAnnotation
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