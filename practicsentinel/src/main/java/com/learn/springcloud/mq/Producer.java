//package com.learn.springcloud.mq;
//
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.client.producer.DefaultMQProducer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Producer {
//    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Producer.class);
//    /**
//     * 生产者的组名
//     */
//    @Value("${ali.rocketmq.producerGroup}")
//    private String producerGroup;
//
//    /**
//     * NameServer 地址
//     */
//    @Value("${ali.rocketmq.namesrvaddr}")
//    private String namesrvAddr;
//
//    @Bean
//    public DefaultMQProducer defaultMQProducer() throws MQClientException {
//        DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
//        producer.setNamesrvAddr(namesrvAddr);
//        producer.setVipChannelEnabled(false);
//        producer.setRetryTimesWhenSendAsyncFailed(2);
//        producer.start();
//        log.info("rocketmq producer server开启成功---------------------------------.");
//        return producer;
//    }
//}
