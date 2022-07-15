package com.learn.springcloud.designpattern.practicedesignpattern.brige;

/**
 * @author sdw
 * @version 1.0
 * @className Benz
 * @date 2021/12/10 14:35:34
 * @description
 */

public class Benz extends  Car{
    Transmission transmission;
    @Override
    public void run() {
        transmission.hashCode();
        //dosomething
    }
}
