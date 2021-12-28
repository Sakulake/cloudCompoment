package com.learn.springcloud.designpattern.practicedesignpattern.decorator;

/**
 * @author sdw
 * @version 1.0
 * @className InputStream
 * @date 2021/12/10 16:46:03
 * @description
 */

public abstract class InputStream {
    public abstract byte[] read();

    public abstract int size();
}
