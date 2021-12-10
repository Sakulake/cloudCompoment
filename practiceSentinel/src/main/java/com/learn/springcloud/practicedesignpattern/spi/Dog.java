package com.learn.springcloud.practicedesignpattern.spi;

/**
 * @author sdw
 * @version 1.0
 * @className Dog
 * @date 2021/12/10 18:00:37
 * @description
 */

public class Dog implements Shut{


    @Override
    public void shout() {
        System.out.println("wang wang");
    }
}
