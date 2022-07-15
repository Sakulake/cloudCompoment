package com.learn.springcloud.javafunc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {
    @Pointcut("@within(com.learn.springcloud.javafunc.aop.MyAnnotation)")
    public void point(){}

    @Around("point()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("自定义注解切面");
        return joinPoint.proceed();
    }
}
