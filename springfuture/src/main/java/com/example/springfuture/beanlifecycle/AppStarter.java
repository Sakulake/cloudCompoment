package com.example.springfuture.beanlifecycle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppStarter {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.springfuture.beanlifecycle");
        ABean bean = (ABean) context.getBean("bean");
        context.registerShutdownHook();
    }
}
