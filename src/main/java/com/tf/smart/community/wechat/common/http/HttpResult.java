package com.tf.smart.community.wechat.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * HTTP 结果
 *
 * @author 翟晶
 */
@Data
@AllArgsConstructor
public class HttpResult {
    // 响应码
    private Integer code;

    // 响应体
    private String body;
}
