package com.tf.smart.community.wechat.common.exception;

/**
 * OAuth 服务异常
 *
 * @author 翟晶
 */
public class OauthServerException extends RuntimeException {
    public OauthServerException(Integer code, String msg) {
        super(String.format("oauth server error, http code: %d, msg: %s", code, msg));
    }
}
