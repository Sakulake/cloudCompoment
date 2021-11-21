package com.learn.springcloud.feignapi;

import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.feignapi.hystrix.MyFeignClientApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author sdw
 * @version 1.0
 * @className MyFeignClientApi
 * @date 2021/11/19 14:26:41
 * @description
 */

@FeignClient(name = "serverProvider", fallback = MyFeignClientApiHystrix.class)
public interface MyFeignClientApi {
    @RequestMapping( value = "/sayHello")
    SayHelloBO sayHello();
}
