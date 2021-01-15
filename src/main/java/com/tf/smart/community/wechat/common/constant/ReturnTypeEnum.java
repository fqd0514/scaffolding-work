package com.tf.smart.community.wechat.common.constant;

import lombok.Getter;

/**
 * 返回类型枚举
 *
 * @author 翟晶
 */
@Getter
public enum ReturnTypeEnum {
    /**
     * 值类型
     */
    VALUE_TYPE(1, "值类型"),
    /**
     * 单对象
     */
    SINGLE_OBJECT(2, "单对象"),
    /**
     * 分页列表
     */
    LIST_WITH_PAGE(3, "分页列表"),
    /**
     * 不分页列表
     */
    LIST_WITHOUT_PAGE(4, "不分页列表");

    private final Integer code;
    private final String description;

    ReturnTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
