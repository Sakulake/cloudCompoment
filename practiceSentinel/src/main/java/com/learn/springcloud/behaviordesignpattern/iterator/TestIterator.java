package com.learn.springcloud.behaviordesignpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sdw
 * @version 1.0
 * @className TestIterator
 * @date 2021/12/15 15:10:09
 * @description 测试remove逻辑
 */

public class TestIterator {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        Iterator<String> iterator1 = names.iterator();
        Iterator<String> iterator2 = names.iterator();
        iterator1.next();
        iterator1.remove();
        iterator2.next(); // 运行结果？
    }
}
