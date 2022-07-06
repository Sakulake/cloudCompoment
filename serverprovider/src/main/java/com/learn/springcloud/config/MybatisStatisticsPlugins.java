package com.learn.springcloud.config;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import java.sql.Statement;
import java.util.Properties;


@Configuration
public class MybatisStatisticsPlugins {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Bean
    public SlowSqlIntercptor slowSqlIntercptor(){
        return new SlowSqlIntercptor(defaultMQProducer);
    }





    @Intercepts({
            @Signature(type = StatementHandler.class ,method = "query", args = {Statement.class, ResultHandler.class}),
            @Signature(type = StatementHandler.class ,method = "update", args = {Statement.class})
    })
    public class SlowSqlIntercptor implements Interceptor{


        public SlowSqlIntercptor(DefaultMQProducer defaultMQProducer) {
            this.defaultMQProducer = defaultMQProducer;
        }

        private DefaultMQProducer defaultMQProducer;

        private String topic = "slow-sql";

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            StopWatch stopWatch  = new StopWatch();
            stopWatch.start();
            Object proceedResult = invocation.proceed();
            stopWatch.stop();
            long time = stopWatch.getLastTaskTimeMillis();
            if (time > 1){
                StatementHandler statementHandler =(StatementHandler) invocation.getTarget();
                String sql = statementHandler.getBoundSql().getSql();
                System.out.println(sql);
                defaultMQProducer.send(new Message(topic,"1".getBytes(RemotingHelper.DEFAULT_CHARSET)),10000);
            }

            return proceedResult;
        }

        @Override
        public Object plugin(Object o) {
            return Plugin.wrap(o, this);
        }

        @Override
        public void setProperties(Properties properties) {

        }
    }
}
