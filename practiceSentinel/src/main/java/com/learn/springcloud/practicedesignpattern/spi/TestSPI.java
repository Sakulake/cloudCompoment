package com.learn.springcloud.practicedesignpattern.spi;

import java.util.ServiceLoader;

/**
 * @author sdw
 * @version 1.0
 * @className TestSPI
 * @date 2021/12/10 17:59:32
 * @description
 */

public class TestSPI {
    public static void main(String[] args) {
        ServiceLoader<Shut>  shutServiceLoader = ServiceLoader.load(Shut.class);
        for (Shut s : shutServiceLoader) {
            s.shout();
        }
    }
}
