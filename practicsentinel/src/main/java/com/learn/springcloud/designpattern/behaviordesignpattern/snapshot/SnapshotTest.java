package com.learn.springcloud.designpattern.behaviordesignpattern.snapshot;

import java.util.Stack;

/**
 * @author sdw
 * @version 1.0
 * @className SnapshotTest
 * @date 2021/12/16 09:40:30
 * @description
 */

public class SnapshotTest {
    Stack<SnapShot> stack = new Stack<SnapShot>();

    public void pushString(SnapShot snapShot){
        stack.push(snapShot);
    }

    public SnapShot popString(){
        return stack.pop();
    }

    public SnapShot peekString(){
        return stack.peek();
    }

}
