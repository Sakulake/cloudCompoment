package com.learn.springcloud.designpattern.practicedesignpattern.proxy;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author sdw
 * @version 1.0
 * @className ProxyTest
 * @date 2021/12/10 09:34:37
 * @description
 * 联系动态代理模式
 */

public class ProxyCGlibTest {
    private Object strongenFunction;


    public void setStrongenFunction(Object strongenFunction) {
        this.strongenFunction = strongenFunction;
    }

    public Object createProxy(Object proxiedObject){

        DynamicProxyHandler proxyHandler = new DynamicProxyHandler();
        Object proxy = proxyHandler.getInstance(proxiedObject);
        return proxy;
    }

    private class DynamicProxyHandler implements MethodInterceptor {
        private Object targetObject;
        public Object getInstance(Object target) {
            // 设置需要创建子类的类
            this.targetObject = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("开启事物");
            Object result = methodProxy.invoke(targetObject, objects);
            System.out.println("关闭事物");
            // 返回代理对象
            return result;
        }
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/mac/IdeaProjects/cloudCompoment/practiceSentinel/src/main/java/com/learn/springcloud/designpattern/practicedesignpattern/proxy/");
        ProxyCGlibTest proxyTest = new ProxyCGlibTest();
        NormalServiceImpl proxy = (NormalServiceImpl)proxyTest.createProxy(new NormalServiceImpl());
        proxy.sayHello();
        System.out.println(proxy.hashCode());
    }

}
