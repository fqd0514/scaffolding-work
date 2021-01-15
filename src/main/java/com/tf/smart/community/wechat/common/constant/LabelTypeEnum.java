package com.tf.smart.community.wechat.common.constant;

import lombok.Getter;

/**
 * 标签类型
 * @Author Leeyoung
 * @Date 2020/8/13
 **/
@Getter
public enum LabelTypeEnum {
    OWNER("1", "居民"), TENANT("2", "租户"),BUILDING("3", "房屋"),FITTING("4", "配件");

    private String code;
    private String description;

    LabelTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
