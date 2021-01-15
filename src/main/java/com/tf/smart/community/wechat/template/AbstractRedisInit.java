package com.tf.smart.community.wechat.template;

import com.tf.smart.community.wechat.common.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: redis 数据初始化模板
 * @Author LeeYoung
 * @Date: 2020/8/19
 */
@Component
public abstract class AbstractRedisInit {

    private Logger logger = LoggerFactory.getLogger(AbstractRedisInit.class);

    /**
     * redis工具类
     */
    @Autowired
    private CacheUtil cacheUtil;

    /**
     * 删除key
     * @param redisKey
     * @return void
     * @Author Leeyoung
     * @Date 2020/8/19
     **/
    public void delKey(String redisKey){
        cacheUtil.del(redisKey);
        logger.info("删除原始 " + redisKey + " 缓存.....");
    }

    /**
     * 插入key数据
     * @param redisKey
     * @return void
     * @Author Leeyoung
     * @Date 2020/8/19
     **/
    public abstract void addKey(String redisKey);

    /**
     * 初始化Redis 相关key的数据
     * @param redisKey
     * @return void
     * @Author Leeyoung
     * @Date 2020/8/19
     **/
    public void initRedisKey(String redisKey) {
        delKey(redisKey);
        addKey(redisKey);
    }
}
