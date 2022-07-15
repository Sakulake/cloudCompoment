package com.learn.springcloud.javafunc.timer;

import org.apache.catalina.Executor;
import org.apache.http.impl.client.FutureRequestExecutionMetrics;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

public class TestTimer {
    public static void main(String[] args) {
        Future future = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });
        ScheduledExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(1);

        Timer timer = new Timer();
        TimerTask timerTask =  new TimerTask() {
            @Override
            public void run() {
                System.out.println(1);
            }
        };
        timer.schedule(timerTask,1000);
        threadPoolExecutor.schedule(timerTask,10, TimeUnit.SECONDS);
    }
}
