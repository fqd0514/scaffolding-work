package com.tf.smart.community.wechat.common.enums;

import com.tf.smart.community.wechat.common.assertion.Assert;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import lombok.Getter;

import java.text.MessageFormat;

/**
 * 通用响应枚举
 *
 * @author 翟晶
 */
@Getter
public enum CommonResponseEnum implements Assert{
    /**
     * 处理成功
     */
    SUCCESS(200, "处理成功"),
    /**
     * 操作失败
     */
    OPERATION_FAILURE(205, "操作失败"),
    /**
     * 数据已存在
     */
    DATA_EXISTED(208, "数据已存在"),
    /**
     * 没有登录
     */
    NEED_LOGIN(401, "没有登录"),
    /**
     * 发生异常
     */
    HAVE_EXCEPTION(402, "发生异常"),
    /**
     * 系统错误
     */
    SYSTEM_ERROR(403, "系统错误"),

    /**
     * 数据已删除
     */
    DATA_ALREADY_DELETE(405, "数据已删除"),

    /**
     * 参数错误
     */
    INVALID_PARAMTER(410, "参数错误"),
    /**
     * 不支持或已废弃
     */
    DEPRECATED(445, "不支持或已废弃"),
    /**
     * 太频繁调用
     */
    TOO_MANY_ATTEMPT(499, "太频繁调用"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(501, "未知错误"),
    /**
     * 权限不足
     */
    NO_PERMISSION(502, "权限不足"),
    /**
     * 没有结果
     */
    RESULT_IS_NULL(911, "没有结果");

    private final Integer code;
    private final String message;

    CommonResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 创建异常
     *
     * @param args
     * @return
     */
    @Override
    public CommonBusinessException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);
        return new CommonBusinessException(this.getCode(),msg);
    }

    @Override
    public CommonBusinessException newException(Throwable t, Object... args) {
        return null;
    }

}
