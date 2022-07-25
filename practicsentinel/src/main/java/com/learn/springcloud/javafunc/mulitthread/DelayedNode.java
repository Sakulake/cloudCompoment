package com.learn.springcloud.javafunc.mulitthread;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedNode implements Delayed {
    private long time;
    private String value;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DelayedNode(String value) {
        this.time = System.currentTimeMillis();
        this.value = value;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedNode node = (DelayedNode)o;
        long diff = time-node.time;
        return diff>0?1:-1;
    }
}
