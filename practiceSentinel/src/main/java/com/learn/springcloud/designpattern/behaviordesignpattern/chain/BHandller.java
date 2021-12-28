package com.learn.springcloud.designpattern.behaviordesignpattern.chain;

/**
 * @author sdw
 * @version 1.0
 * @className AHandller
 * @date 2021/12/14 15:08:22
 * @description
 */

public class BHandller extends ChainHandler{
    @Override
    protected boolean doHandle() {
        return Boolean.TRUE;
    }
}
