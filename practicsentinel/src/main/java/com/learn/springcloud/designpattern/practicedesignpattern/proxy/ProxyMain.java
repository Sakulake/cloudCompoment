package com.learn.springcloud.designpattern.practicedesignpattern.proxy;

import org.springframework.cglib.core.DebuggingClassWriter;

public class ProxyMain {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/code/cloudCompoment/practiceSentinel/src/main/java/com/learn/springcloud/designpattern/practicedesignpattern/proxy/");
        ProxyCGlibTest proxyTest = new ProxyCGlibTest();
        NormalServiceImpl proxy = (NormalServiceImpl)proxyTest.createProxy(new NormalServiceImpl());
        proxy.sayHello();
        System.out.println(proxy.hashCode());
    }
}
