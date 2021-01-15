package com.tf.smart.community.wechat.common.exception;

import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.enums.IResponseEnum;
import lombok.Getter;

/**
 * 通用业务异常
 *
 * @author 翟晶
 */
@Getter
public class CommonBusinessException extends RuntimeException {
    private Integer code;
    private String message;

    public CommonBusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonBusinessException(CommonResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
    }

    public CommonBusinessException(CommonResponseEnum responseEnum, String message) {
        this.code = responseEnum.getCode();
        this.message = message;
    }
}
