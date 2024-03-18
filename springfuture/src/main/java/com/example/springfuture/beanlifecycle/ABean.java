package com.example.springfuture.beanlifecycle;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * 生命周期
 * 1. BeanFactoryPostProcessor##postProcessBeanFactory
 * 2. InstantiationAwareBeanPostProcessor##postProcessBeforeInstartiation()
 * 3. new Bean()
 * 4. InstantiationAwareBeanPostProcessor##postProcessAfterInstartiation() 创建代理对象
 * 5. InstantiationAwareBeanPostProcessor##postProcessPropertise()
 * 6. BeanNameAware##setBeanName()
 * 7. BeanPostProcessor## postProcessBeforeInitialization
 * 8. InitializingBean##AfterPropertiesSet
 * 9. Bean##init
 * 10 BeanPostProcessor## postProcessAfterInitialization
 * 11. DisposableBean##preDestory
 * 12. Bean##destory
 */
@Slf4j
@Data
public class ABean implements BeanNameAware, EnvironmentAware, BeanFactoryAware, ApplicationContextAware , InitializingBean, DisposableBean {
    private String firstProperty;

    private String secondProperty;

    public ABean(){
        log.info("execute User#new User()");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("execute ApplicationContextAware#setApplicationContext");
    }

    @Override
    public void setEnvironment(Environment environment) {
        log.info("execute EnvironmentAware#setEnvironment");
    }

    public void doInit() {
        log.info("execute User#doInit");
    }

    public void doDestroy() {
        log.info("execute User#doDestroy");
    }

    @Override
    public void destroy() throws Exception {
        log.info("execute DisposableBean#destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("execute InitializingBean#afterPropertiesSet");
    }
}
