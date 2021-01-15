package com.tf.smart.community.wechat.common.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * REDIS 锁
 *
 * @author 翟晶
 */
@Service
public class RedisLock {
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String UNLOCK_LUA;

    static {// 释放锁脚本，原子操作
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");

        UNLOCK_LUA = sb.toString();
    }

    /**
     * 获取锁
     *
     * @param lockKey   锁键
     * @param lockValue 锁值
     * @param expire    超期时间
     * @param timeUnit  时间单位
     * @return
     */
    public boolean getLock(String lockKey, String lockValue, long expire, TimeUnit timeUnit) {
        try {
            RedisCallback<Boolean> callback = (connection) -> {
                return connection.set(lockKey.getBytes(Charset.forName("UTF-8")), lockValue.getBytes(Charset.forName("UTF-8")), Expiration.seconds(timeUnit.toSeconds(expire)), RedisStringCommands.SetOption.SET_IF_ABSENT);
            };

            return (Boolean) redisTemplate.execute(callback);
        } catch (Exception e) {
        }

        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey   锁键
     * @param lockValue 锁值
     * @return
     */
    public boolean releaseLock(String lockKey, String lockValue) {
        RedisCallback<Boolean> callback = (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(Charset.forName("UTF-8")), lockValue.getBytes(Charset.forName("UTF-8")));
        };

        return (Boolean) redisTemplate.execute(callback);
    }
}
