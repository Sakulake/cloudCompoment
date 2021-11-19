package com.learn.springcloud.controller;

import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.feignapi.MyFeignClientApi;
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

@RestController
public class FeignClientController implements MyFeignClientApi {
    @Override
    public SayHelloBO sayHello() {
        SayHelloBO helloBO = new SayHelloBO();
        return helloBO ;
    }
//    @RequestMapping("/sayHello")
//    public SayHelloBO sayHello() {
//        SayHelloBO helloBO = new SayHelloBO();
//        return helloBO ;
//    }
}
