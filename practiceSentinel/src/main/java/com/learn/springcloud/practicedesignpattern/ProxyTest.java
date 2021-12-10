package com.learn.springcloud.practicedesignpattern;

import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * @author sdw
 * @version 1.0
 * @className ProxyTest
 * @date 2021/12/10 09:34:37
 * @description
 * 联系动态代理模式
 */

public class ProxyTest {
    private Object strongenFunction;


    public void setStrongenFunction(Object strongenFunction) {
        this.strongenFunction = strongenFunction;
    }

    public Object createProxy(Object proxiedObject){
        Class[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces,handler);
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxiedObject;

        public  DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result = method.invoke(proxiedObject, args);
            strongenFunction.toString();
            return result;
        }
    }
}
