package com.tf.smart.community.wechat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * DTO与entity互转
 * dto/entity继承此接口
 */
public interface PojoConvertor {

    /**
     * 转换为某对象
     * 用fastjson实现序列化和反序列化
     *
     * @param clz
     * @param <T>
     * @return
     */
    default <T> T convertTo(Class<T> clz) {
        return JSON.parseObject(JSON.toJSONBytes(this, SerializerFeature.DisableCircularReferenceDetect), clz);
    }
}
