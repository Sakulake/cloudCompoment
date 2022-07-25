package com.learn.springcloud.designpattern.behaviordesignpattern.expression;

import java.util.Map;

/**
 * @author sdw
 * @version 1.0
 * @className GreaterExpression
 * @date 2021/12/16 16:15:26
 * @description
 */

public class GreaterExpression implements Expression{
    private String key;
    private Long value;
    public GreaterExpression(String strExpression) {
        String[] elements = strExpression.trim().split("\\s+");
        if (elements.length != 3 || !elements[1].trim().equals(">")) {
            throw new RuntimeException("Expression is invalid: " + strExpression);
        }
        this.key = elements[0].trim();
        this.value = Long.parseLong(elements[2].trim());
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (!stats.containsKey(key)) {
            return false;
        }
        long statValue = stats.get(key);
        return statValue > value;
    }
}
