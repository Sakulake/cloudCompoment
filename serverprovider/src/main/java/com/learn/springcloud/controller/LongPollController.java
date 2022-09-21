package com.learn.springcloud.controller;

import com.learn.springcloud.model.DataCenterData;
import com.learn.springcloud.model.LongPollTask;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("longpoll")
public class LongPollController {

    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

    private ConcurrentHashMap<String, ConcurrentLinkedQueue<LongPollTask>> taskMap = new ConcurrentHashMap<>();
    @PostMapping("listen")
    public void listen(HttpServletRequest request , @RequestBody DataCenterData data){
        AsyncContext asyncContext  = request.startAsync();
        LongPollTask longPollTask = new LongPollTask(asyncContext);
        putLongPollTask(data,longPollTask);
        scheduledThreadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                if(longPollTask.isOut()){
                    taskMap.get(data.getDataId()).remove(longPollTask);
                    HttpServletResponse response =  (HttpServletResponse)asyncContext.getResponse();
                    response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
                    asyncContext.complete();
                }
            }
        },30, TimeUnit.SECONDS);
    }

    private void putLongPollTask(DataCenterData data, LongPollTask longPollTask) {
        if(taskMap.get(data.getDataId())==null){
            ConcurrentLinkedQueue<LongPollTask> list = new ConcurrentLinkedQueue<LongPollTask>();
            list.add(longPollTask);
            taskMap.putIfAbsent(data.getDataId(),list);
        }else{
            taskMap.get(data.getDataId()).add(longPollTask);
        }
    }

    @PostMapping("publish")
    public Object publish(@RequestBody DataCenterData data){
        for (String key : taskMap.keySet()){
            if(data.getDataId().equals(key)){
                ConcurrentLinkedQueue<LongPollTask> longPollTasks = taskMap.get(key+"1");
                Iterator iterator =  longPollTasks.iterator();
                while(iterator.hasNext()){
                    LongPollTask longPollTask  = (LongPollTask)iterator.next();
                    longPollTask.setOut(true);
                    AsyncContext asyncContext = longPollTask.getContext();
                    HttpServletResponse response =  (HttpServletResponse)asyncContext.getResponse();
                    response.setStatus(HttpServletResponse.SC_OK);

                    try {
                        response.getWriter().print(data.getData());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    asyncContext.complete();
                }
            }
        }
        return "success";

    }

    /**
     * 测试@Transaction注解什么异常回滚
     * 1. 动态代理时所有的Throwable的子类都被切面逻辑捕获，但是最后还有rule类进行类型校验，如果注解中没有备注，则使用默认判断，是否是Error或者是runnable的子类
     * 2. 在@Transaction注解所在类的方法调用自身方法时，不起作用，应为动态代理生成的切面逻辑，在执行被代理类的逻辑时用的是反射调用目标方法，该方法调用自身的方法时自然不会加入切面逻辑，就是事务管理
     */
    private String throwFlag ="1";
    @Transactional
    @PostMapping("/testTransaction")
    public String testTransaction(@RequestBody Map<String,String> request) throws FileNotFoundException {
        if (throwFlag.equals(request.get("code")) ){
            throw new FileNotFoundException("");
        }else{
            throw new RuntimeException("");
        }
    }
}
