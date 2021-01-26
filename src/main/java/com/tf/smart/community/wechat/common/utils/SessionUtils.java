package com.tf.smart.community.wechat.common.utils;

import com.alibaba.fastjson.JSON;
import com.tf.smart.community.wechat.common.constant.CommonConstant;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import com.tf.smart.community.wechat.entity.auth.ThreadSessionInfo;
import com.tf.smart.community.wechat.entity.auth.UserDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;



/**
 * Session 工具
 *
 * @author 翟晶
 */
@Component
public class SessionUtils {
    @Autowired(required = false)
    public static SessionUtils sessionUtils;
    public static CacheUtil cacheUtil;

    public static ThreadLocal<ThreadSessionInfo> threadLocal = new ThreadLocal<com.tf.smart.community.wechat.entity.auth.ThreadSessionInfo>();

    @Autowired
    public void setCacheUtil(CacheUtil cacheUtil) {
        SessionUtils.cacheUtil = cacheUtil;
    }

    @PostConstruct
    public void init() {
        sessionUtils = this;
    }

    /**
     * 获取用户详情
     *
     * @return {@link UserDetail}
     */
    public static UserDetail getUserDetail() {
        return null;
//        String token = threadLocal.get().getToken();
//        UserDetail userDetail = (UserDetail) JSON.parseObject(user, UserDetail.class);
//        String userId = String.valueOf(cacheUtil.get(CommonConstant.REDIS_TOKEN + token));
//
//        if (StringUtils.isBlank(token) || StringUtils.isBlank(userId)) {
//            throw new CommonBusinessException(CommonResponseEnum.INVALID_PARAMTER, "Token 与用户主键不能为空");
//        }
//
//        try {
////            UserDetail userDetail = authService.getUserDetail(token, userId);
////
////            return userDetail;
//        } catch (Exception ex) {
//            throw new CommonBusinessException(CommonResponseEnum.SYSTEM_ERROR, "获取用户详情失败");
//        }
//        return null;
    }


}
