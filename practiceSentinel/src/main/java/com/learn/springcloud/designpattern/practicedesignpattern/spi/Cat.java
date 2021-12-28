package com.learn.springcloud.designpattern.practicedesignpattern.spi;

/**
 * @author sdw
 * @version 1.0
 * @className Cat
 * @date 2021/12/10 18:01:46
 * @description
 */

public class Cat implements Shut{
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}
