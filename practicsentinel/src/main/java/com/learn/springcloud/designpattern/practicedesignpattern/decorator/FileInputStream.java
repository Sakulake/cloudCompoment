package com.learn.springcloud.designpattern.practicedesignpattern.decorator;

/**
 * @author sdw
 * @version 1.0
 * @className FileInputStream
 * @date 2021/12/10 16:48:14
 * @description
 */

public class FileInputStream extends InputStream{
    InputStream inputStream;

    public FileInputStream(InputStream in) {
        inputStream = in;
    }

    @Override
    public byte[] read() {
        return new byte[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
