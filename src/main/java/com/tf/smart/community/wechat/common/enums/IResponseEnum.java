package com.tf.smart.community.wechat.common.enums;

/**
 * <pre>
 *  异常返回码枚举接口
 * </pre>
 *
 * @author liutong
 * @date 2020/11/3
 */
public interface IResponseEnum {
    /**
     * 获取返回码
     * @return 返回码
     */
    Integer getCode();

    /**
     * 获取返回信息
     * @return 返回信息
     */
    String getMessage();
}
