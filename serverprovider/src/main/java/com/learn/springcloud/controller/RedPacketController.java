package com.learn.springcloud.controller;

import com.learn.springcloud.config.StaticValueConfig;
import com.learn.springcloud.util.DistributeLocker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedPacketController {

    @Autowired
    private DistributeLocker distributeLocker;


    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 创建红包
     * @return
     */
    public String createRedPacket(){
        return null;
    }




    /**
     * 抢红包
     * @return
     */
    public String grabRedPacket(String redPacketId){
//       // 查询redis红包数量
//        redisTemplate.opsForValue().get();
        return "success";
    }


    /**
     * 拆红包
     * @return
     */
    public String unpackRedPacket(String redPacketId){
        // 随机金额 ， 红包个减一
        try {
            if(distributeLocker.tryLock(StaticValueConfig.REDPACKET_UNPACK_PREFIX+redPacketId,5)){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "failure";
        }finally {
            distributeLocker.unLock(StaticValueConfig.REDPACKET_UNPACK_PREFIX+redPacketId);
        }
        return "success";
    }

}
