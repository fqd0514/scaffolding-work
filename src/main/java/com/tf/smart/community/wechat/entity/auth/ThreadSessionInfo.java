package com.tf.smart.community.wechat.entity.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * 线程级别的 Session 信息，用于存放 Token
 *
 * @author 翟晶
 */
@Data
public class ThreadSessionInfo implements Serializable {
    private String token;

    //微信端的token
    private String wechatToken;

    public ThreadSessionInfo(String token) {
        this.token = token;
    }
}
