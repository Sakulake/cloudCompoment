package com.learn.springcloud.controller;

import com.learn.springcloud.bo.SayHelloBO;
import com.learn.springcloud.config.MyConfig;
import com.learn.springcloud.feignapi.MyFeignClientApi;
import com.learn.springcloud.mapper.MiniproUserMapper;
import com.learn.springcloud.po.MiniproUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @author sdw
 * @version 1.0
 * @className FeignClientController
 * @date 2021/11/19 14:39:13
 * @description
 */


@RestController
public class FeignClientController implements MyFeignClientApi {

    private Logger logger =  LogManager.getLogger(this.getClass());

    @Autowired
    private MiniproUserMapper mapper;

    @Autowired
    private MyConfig myConfig;

    @Value("${name:asdf}")
    String name;

    @Override
    @Transactional
    public SayHelloBO sayHello() {
        SayHelloBO helloBO = new SayHelloBO();
        helloBO.setName(name);
        MiniproUser user = mapper.selectByPrimaryKey("1");
        logger.info("1");
        logger.info(myConfig.getName());

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
