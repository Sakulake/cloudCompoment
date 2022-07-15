package com.learn.springcloud.designpattern.behaviordesignpattern.snapshot;

/**
 * @author sdw
 * @version 1.0
 * @className SnapShot
 * @date 2021/12/16 10:15:01
 * @description
 */

public class SnapShot {
    private StringBuilder stringBuilder ;

    public SnapShot(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }

    public void append(String info) {
        stringBuilder.append(info);
    }
}
