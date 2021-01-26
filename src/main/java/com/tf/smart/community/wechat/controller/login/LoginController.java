package com.tf.smart.community.wechat.controller.login;

import com.tf.smart.community.wechat.common.entity.response.CommonResponse;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 登录控制器
 * @Author LeeYoung
 * @Date: 2021/1/26
 */
@RestController
@RequestMapping("/api/login")
@Api(tags = "登录控制器-LeeYoung", value = "登录控制器-LeeYoung")
public class LoginController {

    public CommonResponse login(@RequestBody LoginBodyDTO loginBody){

    }
}
