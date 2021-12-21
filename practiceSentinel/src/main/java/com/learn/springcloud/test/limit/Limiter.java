package com.learn.springcloud.test.limit;

/**
 * @author sdw
 * @date 2021/12/21 17:43:25
 * @description
 *              框架考虑：通用、性能、容错、易拓展
 *              限流规则配置：最小惊奇原则，要支持 .property  .yaml  .xml
 *              限流算法：时间窗口、滑动时间窗口、令牌桶、
 *              限流模式：单应用限流、分布式限流
 *              使用: 代码入侵要低
 */

public interface Limiter {
    boolean limit(String podId,String url);
}
