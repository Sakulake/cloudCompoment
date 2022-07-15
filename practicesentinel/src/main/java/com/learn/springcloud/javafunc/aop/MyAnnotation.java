package com.learn.springcloud.javafunc.aop;

import java.lang.annotation.*;


/**
 * 元注解
 * 就是注解的注解，就是给你自己定义的注解添加注解。你想要你的注解有什么样的功能，此时就需要用元注解对你的注解进行说明
 * @Target 说明了Annotation所修饰的对象范围（类、方法、成员bianliang)如果Target元注解没有出现，那么定义的注解可以应用于程序的任何元素。java.lang.annotatio.ElementType
 *
 *
 * 注意:如果Target元注解没有出现，那么定义的注解可以应用于程序的任何元素。
 *
 * TYPE = 类、接口(包括注解类型) 或enum声明 注解 FIELD =域注解 METHOD=方法注解 PARAMETER =  CONSTRUCTOR = 用于描述构造器  LOCAL_VARIABLE = 用于描述局部变量  PARAMETER = 用于描述参数
 *
 * @Retention  定义了该Annotation被保留的时间长短
 * 某些Annotation仅出现在源代码中，而被编译器丢弃
 * 另一些却被编译在class文件中；编译在class文件中的Annotation可能会被虚拟机忽
 * 另一些在class被装载时将被读取（请注意并不影响class的执行，因为Annotation与class在使用上是被分离的）
 *
 * RetentionPolicy
 * 1.SOURCE:在源文件中有效（即源文件保留）
 * 2.CLASS:在class文件中有效（即class保留）
 * 3.RUNTIME:在运行时有效（即运行时保留）
 * 注解的保留范围,只能三选一
 *
 * @Documented 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API
 *
 * @Inherited  阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
 *
 * @Import 注解是用来导入配置类或者一些需要前置加载的类.
 *
 * Import支持 三种方式
 * 1. 带有@Configuration的配置类(4.2 版本之前只可以导入配置类，4.2版本之后 也可以导入 普通类)
 * 2. ImportSelector 的实现
 * 3. ImportBeanDefinitionRegistrar 的实现
 *
 */


/**
 * 1. 使用@interface自定义注解时，自动继承了java.lang.annotation.Annotation接口，由编译程序自动完成其他细节。在定义注解时，不能继承其他的注解或接口
 * 2. 成员以无参数无异常的方式声明，注意区别一般类成员变量的声明,其中的每一个方法实际上是声明了一个配置参数。方法的名称就是参数的名称.可以使用default为成员指定一个默认值
 * 3. 成员类型是受限的，合法的类型包括原始类型以及String、Class、Annotation、Enumeration
 * 4. 注解类可以没有成员，没有成员的注解称为标识注解，例如JDK注解中的@Override
 * 5. 如果注解只有一个成员，并且把成员取名为value()，则在使用时可以忽略成员名和赋值号“=” ,例如JDK注解的@SuppviseWarnings ；如果成员名 不为value，则使用时需指明成员名和赋值号"="，
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
public @interface MyAnnotation {

    String name() default "David";

}

