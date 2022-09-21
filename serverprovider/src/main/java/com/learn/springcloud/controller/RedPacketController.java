//package com.learn.springcloud.controller;
//
//        import com.learn.springcloud.config.StaticValueConfig;
//        import com.learn.springcloud.util.DistributeLocker;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.core.io.ClassPathResource;
//        import org.springframework.data.redis.core.RedisTemplate;
//        import org.springframework.data.redis.core.script.DefaultRedisScript;
//        import org.springframework.scripting.support.ResourceScriptSource;
//        import org.springframework.web.bind.annotation.RestController;
//
//        import java.util.ArrayList;
//
//@RestController
//public class RedPacketController {
//
//    @Autowired
//    private DistributeLocker distributeLocker;
//
//
//    @Autowired
//    RedisTemplate redisTemplate;
//    /**
//     * 创建红包
//     * @return
//     */
//    public String createRedPacket(){
//        return null;
//    }
//
//
//
//
//    /**
//     * 抢红包
//     * @return
//     */
//    public String grabRedPacket(String redPacketId){
//        // 查询redis红包数量
////        redisTemplate.opsForValue().get() ;
//        return "success";
//    }
//
//
//    /**
//     * 拆红包
//     * @return
//     */
//    public String unpackRedPacket(String redPacketId){
//        // 随机金额 ， 红包个减一
//        try {
//            if(distributeLocker.tryLock(StaticValueConfig.REDPACKET_UNPACK_PREFIX+redPacketId,5)){
//                DefaultRedisScript<Boolean>defaultRedisScript = new DefaultRedisScript<Boolean>();
//                defaultRedisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/grab.lua")));
//                ArrayList<String> keys = new ArrayList<>();
//                keys.add(StaticValueConfig.REDPACKET_UNPACK_PREFIX+redPacketId);
//                keys.add(StaticValueConfig.REDPACKET_GRAB_MAX_COUNT);
//                Boolean result = (Boolean)redisTemplate.execute(defaultRedisScript,keys,new ArrayList<>());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return "failure";
//        }finally {
//            distributeLocker.unLock(StaticValueConfig.REDPACKET_UNPACK_PREFIX+redPacketId);
//        }
//        return "success";
//    }
//
//}
