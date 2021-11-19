package com.learn.springcloud.feignapi.hystrix;

import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.feignapi.MyFeignClientApi;
import org.springframework.stereotype.Component;

@Component
public class MyFeignClientApiHystrix implements MyFeignClientApi {

    public SayHelloBO sayHello() {
        SayHelloBO sayHelloBO = new SayHelloBO();
        sayHelloBO.setName("sorry");
        return sayHelloBO;
    }
}
