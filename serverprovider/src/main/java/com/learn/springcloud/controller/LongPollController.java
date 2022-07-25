package com.learn.springcloud.controller;

import com.learn.springcloud.model.DataCenterData;
import com.learn.springcloud.model.LongPollTask;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
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
}
