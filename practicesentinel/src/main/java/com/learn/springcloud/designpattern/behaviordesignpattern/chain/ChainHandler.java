package com.learn.springcloud.designpattern.behaviordesignpattern.chain;

import java.util.LinkedList;
import java.util.List;

/**
 * @author sdw
 * @version 1.0
 * @className ChainHandler
 * @date 2021/12/14 15:06:17
 * @description
 */

public abstract class ChainHandler {


    List<ChainHandler> chains= new LinkedList<>();



    public void addHandler(ChainHandler chainHandler){
        chains.add(chainHandler);
    }

    public boolean handle(){
        for(ChainHandler handler :chains){
            if(!handler.doHandle()){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    protected abstract boolean doHandle();
}
