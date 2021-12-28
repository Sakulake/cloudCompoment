package com.learn.springcloud.designpattern.behaviordesignpattern.snapshot;

import java.util.Scanner;

/**
 * @author sdw
 * @version 1.0
 * @className TestSnapshao
 * @date 2021/12/16 09:43:35
 * @description
 */

public class TestSnapshot {
    public static void main(String[] args) {
        SnapShot snapShot = new SnapShot( new StringBuilder());
        SnapshotTest snap = new SnapshotTest();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            String info = scanner.next();
            if(":list".equals(info)){
                System.out.println(snapShot.toString());
            }else if(":undo".equals(info)){
                snap.popString();
                SnapShot s = snap.peekString();
                System.out.println(s.toString());
                snapShot = s;
            }else {
                snapShot.append(info);
                snap.pushString(snapShot);
                snapShot = new SnapShot(new StringBuilder(snapShot.toString()));
            }
        }
    }
}
