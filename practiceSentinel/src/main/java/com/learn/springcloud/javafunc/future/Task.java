package com.learn.springcloud.javafunc.future;

public class Task implements Runnable {
    private Result result;

    public Task(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        result.setTime(String.valueOf(System.currentTimeMillis()));
        result.setResultMsg("success");
    }
}
