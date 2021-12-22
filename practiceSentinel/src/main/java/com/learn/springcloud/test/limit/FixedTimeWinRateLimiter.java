package com.learn.springcloud.test.limit;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import com.learn.springcloud.test.limit.config.ConfigFactory;
import com.learn.springcloud.test.limit.config.entity.LimitConfig;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

public class FixedTimeWinRateLimiter implements Limiter{
    private LimitConfig limitConfig = ConfigFactory.getLimitConfig("file");

    /* timeout for {@code Lock.tryLock() }. */
    private static final long TRY_LOCK_TIMEOUT = 200L;  // 200ms.
    private Stopwatch stopwatch;
    private AtomicInteger currentCount = new AtomicInteger(0);
    private final int limit;
    private Lock lock = new ReentrantLock();

    public FixedTimeWinRateLimiter(int limit) {
        this(limit, Stopwatch.createStarted());
    }

    @VisibleForTesting
    protected FixedTimeWinRateLimiter(int limit, Stopwatch stopwatch) {
        this.limit = limit;
        this.stopwatch = stopwatch;
    }

    public boolean tryAcquire() throws Exception {
        int updatedCount = currentCount.incrementAndGet();
        if (updatedCount <= limit) {
            return true;
        }

        try {
            if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                try {
                    if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
                        currentCount.set(0);
                        stopwatch.reset();
                    }
                    updatedCount = currentCount.incrementAndGet();
                    return updatedCount <= limit;
                } finally {
                    lock.unlock();
                }
            } else {
                throw new Exception("tryAcquire() wait lock too long:" + TRY_LOCK_TIMEOUT + "ms");
            }
        } catch (InterruptedException e) {
            throw new Exception("tryAcquire() is interrupted by lock-time-out.", e);
        }
    }

    @Override
    public boolean limit(String podId, String url) {
        try {
            return this.tryAcquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
