package com.tf.smart.community.wechat.common.exception;

/**
 * Token 错误异常
 *
 * @author 翟晶
 */
public class TokenBadException extends RuntimeException {
    public TokenBadException(String msg) {
        super(msg);
    }
}
