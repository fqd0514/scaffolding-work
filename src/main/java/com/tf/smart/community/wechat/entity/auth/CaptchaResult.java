package com.tf.smart.community.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 验证码结果
 *
 * @author 翟晶
 */
@ApiModel("验证码结果")
@Data
public class CaptchaResult implements Serializable {
    @ApiModelProperty("验证码图片 Base64")
    private String captchaImg;

    @ApiModelProperty("验证码序列号")
    private String captchaIndex;

    public String getCaptchaImg() {
        return "data:image/png;base64," + captchaImg;
    }
}
