package com.learn.springcloud.test;

public class ClassTest {
    String str = new String("hello");

    char[] ch = {'a','b','c'};
    public void fun(String str,char [] ch ){
        System.out.println(System.identityHashCode(str));
        str = "world";
        System.out.println(System.identityHashCode(str));
        ch[0] ='d';
    }

    static int s = 1;
    private float f = 1f;
    double d = 1d;

    public static void main(String[] args) {
        ClassTest test1 = new ClassTest();
        test1.fun(test1.str,test1.ch);
        System.out.println(System.identityHashCode(test1.str));
        System.out.println(test1.str + "-----"+test1.ch.toString());
        int a = 1;
        int b = 2;
        Object o = new Object();
        test1.f =1;
        int i =23;
        float f = 25;
        float h = f+i;
    }
}
