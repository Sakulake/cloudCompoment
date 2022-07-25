package com.learn.springcloud.designpattern.behaviordesignpattern.expression;

import java.util.Map;

/**
 * @author sdw
 * @date 2021/12/16 15:20:47
 * @description
 */

public interface Expression {
    boolean interpret(Map<String,Long> stats);
}
