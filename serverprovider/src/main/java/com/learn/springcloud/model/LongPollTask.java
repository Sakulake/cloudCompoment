package com.learn.springcloud.model;

import javax.servlet.AsyncContext;

public class LongPollTask {
    private AsyncContext context;
    private boolean out;

    public LongPollTask(AsyncContext asyncContext) {
        context= asyncContext;
        out = false;
    }

    public AsyncContext getContext() {
        return context;
    }

    public void setContext(AsyncContext context) {
        this.context = context;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }
}
