package com.learn.springcloud.javafunc.mulitthread;

import java.util.concurrent.DelayQueue;

public class DelayedQueenTest {
    public static void main(String[] args) {
        DelayQueue<DelayedNode> queue = new DelayQueue<>();
        queue.put(new DelayedNode("1"));
        queue.poll();
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
