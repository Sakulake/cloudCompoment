//package com.learn.springcloud.feignapi;
//
//import com.learn.springcloud.bo.SayHelloBO;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
///**
// * @author sdw
// * @version 1.0
// * @className MyFeignClientApi
// * @date 2021/11/19 14:26:41
// * @description
// */
//
//@FeignClient(name = "serverProvider", url = "http://127.0.0.1:8001")
//public interface MyFeignClientApi {
//    @RequestMapping(method = RequestMethod.GET, value = "/sayHello")
//    SayHelloBO sayHello();
//}
