package com.learn.springcloud.designpattern.behaviordesignpattern.command;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author sdw
 * @version 1.0
 * @className TsetCommand
 * @date 2021/12/16 10:52:33
 * @description
 */

public class TsetCommand {
    public static final int THREAD_NUM = 10;
    private static  Queue<Command> commands  = new LinkedList<>();
    public static void main(String[] args) {

        int requestType = new Random().nextInt(5);
        while (true){
            if (requestType == 1) {
                Command command = new Command() {
                    @Override
                    public void excute() {
                        // TODO: 2021/12/16
                    }
                };
                commands.add(command);
            }else if (requestType == 2){
                Command command = new Command() {
                    @Override
                    public void excute() {
                        // TODO: 2021/12/16
                    }
                };
                commands.add(command);
            }
        }
    }
}
