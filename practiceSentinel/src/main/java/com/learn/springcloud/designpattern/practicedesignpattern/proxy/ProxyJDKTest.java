package com.learn.springcloud.designpattern.practicedesignpattern.proxy;



import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author sdw
 * @version 1.0
 * @className ProxyTest
 * @date 2021/12/10 09:34:37
 * @description
 * 联系动态代理模式
 */
@Aspect

public class ProxyJDKTest {
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
            System.out.println("proxy ----1234");
            return null;
        }
    }

    public static void main(String[] args) {
        System.getProperties().setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ProxyJDKTest proxyTest = new ProxyJDKTest();
        NormalService proxy =  (NormalService)proxyTest.createProxy(new NormalServiceImpl());
        proxy.sayHello();
    }


}
