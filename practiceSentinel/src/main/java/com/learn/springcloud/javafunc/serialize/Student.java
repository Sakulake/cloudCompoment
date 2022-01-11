package com.learn.springcloud.javafunc.serialize;

import java.io.Serializable;

/**
 * @author sdw
 * @version 1.0
 * @className Student
 * @date 2022/01/11 18:01:30
 * @description
 */

public class Student implements Serializable {
    private String name;
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
