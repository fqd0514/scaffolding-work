package com.tf.smart.community.wechat.common.aspec;

import com.alibaba.fastjson.JSON;
import com.tf.smart.community.wechat.common.constant.CommonConstant;
import com.tf.smart.community.wechat.common.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 日志切面
 *
 * @author 翟晶
 */
@Slf4j
@Component
@Aspect
public class LogAspect {
    private final String pointcut = "execution(* com.tf.smart.community.wechat.controller..*.*(..))";

    @Pointcut(value = pointcut)
    public void log() {
    }

    /**
     * 请求日志
     *
     * @param joinPoint
     */
    @Before("log()")
    public void requestLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer sbRequest = new StringBuffer();

        sbRequest
                .append("请求信息：").append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("URL: ").append(request.getRequestURI()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("HttpMethod: ").append(request.getMethod()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("IP: ").append(request.getRemoteAddr()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("SessionId: ").append(request.getSession().getId()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("ClassMethod: ").append(joinPoint.getSignature().getDeclaringTypeName()).append(".").append(joinPoint.getSignature().getName()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("Date: ").append(DateTimeUtils.getCommonCurrentDateStr()).append(CommonConstant.SYSTEM_LINE_SEPARATOR);

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        Map<String, String[]> parameterMap = requestWrapper.getParameterMap();
        if (ObjectUtils.isNotEmpty(parameterMap)) {
            sbRequest.append("RequestParamters: ").append(JSON.toJSONString(parameterMap));
        }

        log.info(sbRequest.toString());
    }


    /**
     * 响应日志
     *
     * @param resultVO
     */
    @AfterReturning(returning = "resultVO", pointcut = "log()")
    public void responseLog(Object resultVO) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        StringBuffer sbResponse = new StringBuffer();

        sbResponse
                .append("响应信息: ").append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("URL: ").append(request.getRequestURI()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("SessionId: ").append(request.getSession().getId()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("Date: ").append(DateTimeUtils.getCommonCurrentDateStr()).append(CommonConstant.SYSTEM_LINE_SEPARATOR)
                .append("响应结果: ").append(JSON.toJSONString(resultVO));

        log.info(sbResponse.toString());
    }
}
