package com.learn.springcloud.designpattern.behaviordesignpattern.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sdw
 * @version 1.0
 * @className OrExpression
 * @date 2021/12/16 15:27:06
 * @description
 */

public class OrExpression implements Expression{
    List<Expression> expressions = new ArrayList<>();

    public OrExpression(String strOrExpression) {

    }

    public OrExpression( List<Expression> expression) {
        this.expressions.addAll(expression);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression expr : expressions) {
            if (expr.interpret(stats)) {
                return true;
            }
        }
        return false;
    }
}
