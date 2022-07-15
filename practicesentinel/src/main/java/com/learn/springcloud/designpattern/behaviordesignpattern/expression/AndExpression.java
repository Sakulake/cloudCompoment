package com.learn.springcloud.designpattern.behaviordesignpattern.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sdw
 * @version 1.0
 * @className AndExpressions
 * @date 2021/12/16 15:42:51
 * @description
 */

public class AndExpression implements Expression{
    List<Expression> expressions = new ArrayList<>();

    public AndExpression(List<Expression> expressions) {
        this.expressions.addAll(expressions);
    }

    public AndExpression(String strAndExpression) {

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
