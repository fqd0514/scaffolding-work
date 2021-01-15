package com.tf.smart.community.wechat.common.enums;

/**
 * @author zlx
 * @date 2020/6/28 17:56
 * @description 状态枚举类
 */
public enum StatusEnum {

    DELETE(-1, "已删除"),
    ENABLE(1, "启用"),
    DISABLE(2, "禁用");

    private Integer code;
    private String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
