package com.learn.springcloud.service.impl;

import com.learn.springcloud.service.Hello;

public class DavidHello implements Hello {
    @Override
    public String say() {
        return "David,"+msg;
    }
}
