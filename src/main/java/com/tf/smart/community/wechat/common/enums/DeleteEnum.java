package com.tf.smart.community.wechat.common.enums;

/**
 * 删除枚举
 * @Author Leeyoung
 * @Date 2021/1/28
 **/
public enum DeleteEnum {

    IS_NOT_DELETE("0","未删除"),PUBLISHED("1","已删除");

    private String code;
    private String value;

    DeleteEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}
