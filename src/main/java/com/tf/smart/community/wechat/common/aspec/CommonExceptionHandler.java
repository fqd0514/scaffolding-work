package com.tf.smart.community.wechat.common.aspec;

import com.tf.smart.community.wechat.common.entity.response.CommonResponse;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import com.tf.smart.community.wechat.common.exception.OauthServerException;
import com.tf.smart.community.wechat.common.exception.TokenBadException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 通用异常处理器
 *
 * @author 翟晶
 */
@Slf4j
@ControllerAdvice
public class CommonExceptionHandler {
    /**
     * OAuth 服务异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({OauthServerException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Object oauthServerExceptionHandler(OauthServerException ex) {
        log.info("oauth server exception:{}", ex.getMessage());

        return CommonResponse.error(CommonResponseEnum.OPERATION_FAILURE, ex.getMessage());
    }

    /**
     * Token 错误异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({TokenBadException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Object tokenBadException(TokenBadException ex) {
        log.info("token bad exception:{}", ex.getMessage());

        return CommonResponse.error(CommonResponseEnum.NEED_LOGIN, ex.getMessage());
    }

    /**
     * 通用业务异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({CommonBusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object commonBusinessExceptionHandler(CommonBusinessException ex) {
        log.info("通用业务异常：{}", ex.getMessage());

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(ex.getCode());
        commonResponse.setMessage(ex.getMessage());
        return commonResponse;
    }

    /**
     * 绑定异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object exceptionHandler(BindException ex) {
        log.info("绑定异常：{}", ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

        return CommonResponse.error(CommonResponseEnum.INVALID_PARAMTER, ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     * 方法参数校验失败异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object validErrorHandler(MethodArgumentNotValidException ex) {
        log.info("方法参数校验失败异常：%s", ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

        return CommonResponse.error(CommonResponseEnum.INVALID_PARAMTER, ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     * 异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object exceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("异常：{}", ExceptionUtils.getStackTrace(ex));

        return CommonResponse.error(CommonResponseEnum.INVALID_PARAMTER,"Http请求方法错误,请确定接口支持的Http方法类型");
    }

    /**
     * 异常处理器
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object exceptionHandler(Exception ex) {
        log.error("异常：{}", ExceptionUtils.getStackTrace(ex));

        return CommonResponse.error(CommonResponseEnum.SYSTEM_ERROR);
    }
}
