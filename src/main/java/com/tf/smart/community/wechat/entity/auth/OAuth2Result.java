package com.tf.smart.community.wechat.entity.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * OAuth2 结果
 *
 * @author 翟晶
 */
@Data
public class OAuth2Result implements Serializable {
    // 响应状态
    private String code;

    // 响应信息
    private String message;

    // 数据
    private String data;
}
