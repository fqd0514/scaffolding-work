package com.tf.smart.community.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 登录请求
 *
 * @author 翟晶
 */
@ApiModel("登录请求")
@Data
public class LoginRequest implements Serializable {
    @ApiModelProperty("登录名")
    private String loginName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String captchaCode;

    @ApiModelProperty("验证码序列号")
    private String captchaIndex;
}
