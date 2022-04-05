package com.learn.springcloud.controller;

import com.alibaba.fastjson.JSONObject;
import com.learn.springcloud.bo.RokectMQMessage;
import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.config.StudentConfig;
import com.learn.springcloud.designpattern.practicedesignpattern.proxy.NormalService;
import com.learn.springcloud.feignapi.MyFeignClientApi;
import com.learn.springcloud.mq.RocketMQProducerTest;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sdw
 * @date 2021/11/18 10:31:08
 * @description
 */
@RefreshScope
@RestController
public class IndexController {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(IndexController.class);


    @Value("${description:zy}")
    private String description;

    @Autowired
    private StudentConfig studentConfig;

    @Autowired
    private MyFeignClientApi myFeignClientApi;
    @Autowired
    NormalService normalService;


    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @RequestMapping("/description")
    public String testDescription() {
        System.out.println("description is : " + description);
        return description;
    }

    @RequestMapping("/config")
    public String testConfig() {
        System.out.println(studentConfig.toString());
        return studentConfig.toString();
    }

    @RequestMapping("/sayHello")
    public String sayHello() {
        SayHelloBO helloBO = myFeignClientApi.sayHello();
        log.info(helloBO.toString());
        return helloBO.toString();
    }

    @RequestMapping("/sayHelloAOP")
    public String sayHelloAOP() {
        normalService.sayHello();

        return "success";
    }

    @RequestMapping("/testProducer")
    public String testProducer(@RequestBody  RokectMQMessage params) {
        try {
            defaultMQProducer.send(new Message("springboot-topic", "testProducer", JSONObject.toJSONString(params).getBytes(RemotingHelper.DEFAULT_CHARSET)));
        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "success";
    }


    @Autowired
    RedisTemplate redisTemplate;
    @RequestMapping("/testRedis")
    public String testRedis(){
        return  ( String)redisTemplate.opsForValue().get("testRedis");
    }

}
