package com.tf.smart.community.wechat.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.collect.Maps;
import com.tf.smart.community.wechat.common.entity.response.CommonResponse;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import com.tf.smart.community.wechat.common.http.HttpAPIService;
import com.tf.smart.community.wechat.common.http.HttpResult;
import com.tf.smart.community.wechat.common.utils.CacheUtil;
import com.tf.smart.community.wechat.common.utils.SessionUtils;
import com.tf.smart.community.wechat.entity.auth.CaptchaResult;
import com.tf.smart.community.wechat.entity.auth.LoginRequest;
import com.tf.smart.community.wechat.entity.auth.OAuth2Result;
import com.tf.smart.community.wechat.entity.auth.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Map;

/**
 * 权限服务
 *
 * @author 翟晶
 */
@Slf4j
@Service
public class AuthService {
    public static final String REDIS_LOGIN_CAPTCHA_CODE = "login_captcha_code_%s";
    public static final String REDIS_OAUTH_SERVER_TOKEN = "smart:community:auth:token:";
    private static final String OAUTH_TOKEN_URI = "/oauth/token";
    private static final String USER_ROLE_MENU_URI = "/api/interface/getUserInfo";
    private static final String USER_BUTTON_URI = "/api/interface/listPermissionByUserId";
    private static final String LOGOUT_URI = "/logout";
    private static final String OAUTH_CHECK_TOKEN_URI = "/oauth/check_token";

    //微信用户的token
    public static final String REDIS_WECHAT_TOKEN = "wechat:token:";

    @Value("${oauth2.domain}")
    private String oAuth2Domain;

    @Value("${oauth2.resource_server.domain}")
    private String oAuth2ResourceServerDomain;

    @Value("${oauth2.client.id}")
    private String oAuth2ClientId;

    @Value("${oauth2.client.secret}")
    private String oAuth2ClientSecret;

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private HttpAPIService httpAPIService;

    @PostConstruct
    public void init() {
        SessionUtils.authService = this;
    }

    /**
     * 登录校验
     *
     * @param loginRequest
     * @return
     * @throws Exception
     */
    public CommonResponse auth(LoginRequest loginRequest) throws Exception {
        String loginName = loginRequest.getLoginName();
        String password = loginRequest.getPassword();
        /*String captchaCode = loginRequest.getCaptchaCode();
        String captchaIndex = loginRequest.getCaptchaIndex();

        String redisCaptchaCode = String.valueOf(cacheUtil.get(String.format(REDIS_LOGIN_CAPTCHA_CODE, captchaIndex)));
        if (StringUtils.isBlank(redisCaptchaCode)) {
            return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, "验证码过期");
        } else if (!StringUtils.equals(redisCaptchaCode, captchaCode)) {
            return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, "验证码有误");
        } else {
            cacheUtil.del(String.format(REDIS_LOGIN_CAPTCHA_CODE, captchaIndex));
        }*/

        Map<String, Object> map = Maps.newHashMap();
        map.put("username", loginName);
        map.put("password", password);
        map.put("grant_type", "password");
        map.put("client_id", oAuth2ClientId);
        map.put("client_secret", oAuth2ClientSecret);

        HttpResult httpResult = httpAPIService.doPost(oAuth2Domain + OAUTH_TOKEN_URI, map);
        if (Integer.compare(HttpStatus.SC_OK, httpResult.getCode()) == 0) {
            OAuth2Result oAuth2Result = JSONObject.parseObject(httpResult.getBody(), OAuth2Result.class);

            if (StringUtils.equals(CommonResponseEnum.SUCCESS.getCode().toString(), oAuth2Result.getCode())) {
                JSONObject jsonObject = JSONObject.parseObject(oAuth2Result.getData());

                String userId = jsonObject.getString("userId");
                String access_token = jsonObject.getString("access_token");

                CommonResponse userRoleMenu = getUserRoleMenuInfo(access_token, "2", userId);
                if (Integer.compare(CommonResponseEnum.SUCCESS.getCode(), userRoleMenu.getCode()) != 0) {
                    return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, userRoleMenu.getMessage());
                }

                CommonResponse userButton = getUserButtonInfo(access_token, "2", userId);
                if (Integer.compare(CommonResponseEnum.SUCCESS.getCode(), userButton.getCode()) != 0) {
                    return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, userButton.getMessage());
                }

                jsonObject.put("userInfo", userRoleMenu.getData());
                jsonObject.put("foreMenuBtnInfo", userButton.getData());

                cacheUtil.set(REDIS_OAUTH_SERVER_TOKEN + access_token, userId);

                return CommonResponse.success(jsonObject);
            } else {
                return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, oAuth2Result.getMessage());
            }
        } else {
            return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 获取用户角色、菜单信息
     *
     * @param access_token
     * @param platformId
     * @param userId
     * @return
     * @throws Exception
     */
    public CommonResponse getUserRoleMenuInfo(String access_token, String platformId, String userId) throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "bearer " + access_token);

        Map<String, Object> map = Maps.newHashMap();
        map.put("platformId", platformId);
        map.put("userId", userId);

        HttpResult httpResult = httpAPIService.doGetWithHeader(oAuth2ResourceServerDomain + USER_ROLE_MENU_URI, headers, map);

        if (Integer.compare(HttpStatus.SC_OK, httpResult.getCode()) == 0) {
            OAuth2Result oAuth2Result = JSONObject.parseObject(httpResult.getBody(), OAuth2Result.class);

            if (StringUtils.equals(CommonResponseEnum.SUCCESS.getCode().toString(), oAuth2Result.getCode())) {
                return CommonResponse.success(JSONObject.parseObject(oAuth2Result.getData()));
            } else {
                return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, oAuth2Result.getMessage());
            }
        } else {
            return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 获取用户按钮信息
     *
     * @param access_token
     * @param platformId
     * @param userId
     * @return
     * @throws Exception
     */
    public CommonResponse getUserButtonInfo(String access_token, String platformId, String userId) throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "bearer " + access_token);

        Map<String, Object> map = Maps.newHashMap();
        map.put("platformId", platformId);
        map.put("userId", userId);

        HttpResult httpResult = httpAPIService.doGetWithHeader(oAuth2ResourceServerDomain + USER_BUTTON_URI, headers, map);

        if (Integer.compare(HttpStatus.SC_OK, httpResult.getCode()) == 0) {
            OAuth2Result oAuth2Result = JSONObject.parseObject(httpResult.getBody(), OAuth2Result.class);

            if (StringUtils.equals(CommonResponseEnum.SUCCESS.getCode().toString(), oAuth2Result.getCode())) {
                return CommonResponse.success(JSONArray.parseArray(oAuth2Result.getData()));
            } else {
                return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, oAuth2Result.getMessage());
            }
        } else {
            return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 登出
     *
     * @param access_token
     * @return
     */
    public CommonResponse logout(String access_token) throws Exception {
        Map<String, String> headers = Maps.newHashMap();
        headers.put("Authorization", "bearer " + access_token);

        HttpResult httpResult = httpAPIService.doPost(oAuth2Domain + LOGOUT_URI, null, headers);

        if (Integer.compare(HttpStatus.SC_OK, httpResult.getCode()) == 0) {
            OAuth2Result oAuth2Result = JSONObject.parseObject(httpResult.getBody(), OAuth2Result.class);

            if (StringUtils.equals(CommonResponseEnum.SUCCESS.getCode().toString(), oAuth2Result.getCode())) {
                return CommonResponse.success();
            } else {
                return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, oAuth2Result.getMessage());
            }
        } else {
            return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE);
        }
    }

    /**
     * 校验 Token
     *
     * @param token
     * @return
     */
    public Boolean checkToken(String token) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("token", token);

        try {
            HttpResult httpResult = httpAPIService.doPost(oAuth2Domain + OAUTH_CHECK_TOKEN_URI, map);

            if (Integer.compare(HttpStatus.SC_OK, httpResult.getCode()) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.error("校验 Token 异常，Token：{}，异常：{}", token, ex.getMessage());
        }

        return false;
    }

    /**
     * 获取用户详情
     *
     * @param token
     * @param userId
     * @return
     * @throws Exception
     */
    public UserDetail getUserDetail(String token, String userId) throws Exception {
        CommonResponse userRoleMenu = getUserRoleMenuInfo(token, "2", userId);

        return JSONObject.parseObject(userRoleMenu.getData().toString(), UserDetail.class);
    }

    /**
     * 获取验证码图片
     *
     * @param request
     * @param response
     * @param captchaProducer
     * @return
     */
    public CommonResponse getCaptchaImg(HttpServletRequest request, HttpServletResponse response, DefaultKaptcha captchaProducer) {
        try {
            CaptchaResult captchaResult = new CaptchaResult();
            captchaResult.setCaptchaImg(genCaptchaImg(request, response, captchaProducer));
            captchaResult.setCaptchaIndex(request.getSession().getId());

            return CommonResponse.success(captchaResult);
        } catch (Exception ex) {
            throw new CommonBusinessException(CommonResponseEnum.SYSTEM_ERROR, "验证码生成失败");
        }
    }

    private String genCaptchaImg(HttpServletRequest request, HttpServletResponse response, DefaultKaptcha captchaProducer) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        cacheUtil.set(String.format(REDIS_LOGIN_CAPTCHA_CODE, request.getSession().getId()), capText, 600);

        BufferedImage bi = captchaProducer.createImage(capText);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);

        return new BASE64Encoder().encodeBuffer(baos.toByteArray()).trim().replaceAll("\n", "").replaceAll("\r", "");
    }
}
