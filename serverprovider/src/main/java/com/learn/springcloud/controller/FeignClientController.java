package com.learn.springcloud.controller;

import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.feignapi.MyFeignClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sdw
 * @version 1.0
 * @className FeignClientController
 * @date 2021/11/19 14:39:13
 * @description
 */

@RefreshScope
@RestController
public class FeignClientController implements MyFeignClientApi {

    @Value("${name:asdf}")
    String name;

    @Override
    public SayHelloBO sayHello() {
        SayHelloBO helloBO = new SayHelloBO();
        helloBO.setName(name);
        System.out.println(1);
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return helloBO ;
    }
//    @RequestMapping("/sayHello")
//    public SayHelloBO sayHello() {
//        SayHelloBO helloBO = new SayHelloBO();
//        return helloBO ;
//    }
}
