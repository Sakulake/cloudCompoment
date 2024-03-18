package com.example.springfuture.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigA {
    @Bean(name = "bean" ,initMethod = "doInit",destroyMethod = "doDestroy")
    public ABean bean(){
        ABean bean = new ABean();
        bean.setFirstProperty("first");
        bean.setSecondProperty("second");
        return bean;
    }
}
