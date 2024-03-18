package com.learn.springcloud.designpattern.practicedesignpattern.decorator;

/**
 * @author sdw
 * @version 1.0
 * @className BufferInputStream
 * @date 2021/12/10 16:49:21
 * @description
 */

public class BufferInputStream extends FileInputStream{
    protected volatile InputStream in;
    protected BufferInputStream(InputStream in) {
        super(in);
        this.in = in;
    }

    @Override
    public byte[] read() {
        //strongerFunction
        return null;
    }

    public static void main(String[] args) {
        InputStream in = new FileInputStream(null);
        BufferInputStream bufferInputStream = new BufferInputStream(in);
    }
}
