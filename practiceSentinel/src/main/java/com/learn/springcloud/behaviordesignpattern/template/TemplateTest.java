package com.learn.springcloud.behaviordesignpattern.template;

//复用，例如InputStream read  AbstractList add
//拓展， servlet service
public abstract class TemplateTest {
    final void method1(){
        method2();
        method3();
    }

    protected abstract void method3();

    protected abstract void method2();


}
