package com.learn.springcloud.designpattern.behaviordesignpattern.strategy;

import java.util.HashMap;

/**
 * @author sdw
 * @version 1.0
 * @className StrategyFactory
 * @date 2021/12/14 14:03:40
 * @description
 */

public class StrategyFactory {
    private HashMap<String,Strategy> strategyHashMap;
    {
        strategyHashMap = new HashMap<>();
        strategyHashMap.put("first",new FirstStrategy());
        strategyHashMap.put("second",new SecondStrategy());
    }


    public Strategy getStrategy(String type){
        return strategyHashMap.get(type);
    }
}
