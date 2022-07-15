package com.learn.springcloud.javafunc.future;



import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,5, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
//
//        Result result = new Result();
//        Task task = new Task(result);
//        Future<Result> future = threadPoolExecutor.submit(task,result);// task reuslt 封住成FutureTask
//
//        try {
//            Result rr = future.get();
//            System.out.println(rr.getResultMsg());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        int i = new Integer(0);
        FutureTask<Integer> futureTask = new FutureTask(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("start to run callable");
                Long start = System.currentTimeMillis();
                while (true) {
                    Long current = System.currentTimeMillis();
                    if ((current - start) > 1000) {
                        System.out.println("当前任务执行已经超过1s");
                        return 1;
                    }
                }
            }
        });
        Future future = threadPoolExecutor.submit(futureTask);
        try {
            Thread.currentThread().sleep(3000);
            threadPoolExecutor.shutdown();
        } catch (Exception e) {
            //NO OP
        }

        try {
            System.out.println("执行结果"+futureTask.get());
            System.out.println("执行结果"+future.get(5,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
