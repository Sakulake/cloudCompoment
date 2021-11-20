package com.learn.springcloud.controller;

import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.config.StudentConfig;
import com.learn.springcloud.feignapi.MyFeignClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
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



    @Value("${description:zy}")
    private String description;

    @Autowired
    private StudentConfig studentConfig;

    @Autowired
    private MyFeignClientApi myFeignClientApi;

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
        System.out.println(helloBO);
        return helloBO.toString();
    }

}
