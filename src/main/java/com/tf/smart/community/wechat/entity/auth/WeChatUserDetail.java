package com.tf.smart.community.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户详情
 *
 * @author Leeyoung
 */
@ApiModel("微信用户详情")
@Data
public class WeChatUserDetail implements Serializable {

    /**
     * 用户主键
     */
    @ApiModelProperty("用户主键")
    private Long id;

    /**
     * 微信OPENID
     */
    @ApiModelProperty("微信OPENID")
    private String openid;

    /**
     * 微信昵称
     */
    @ApiModelProperty("微信昵称")
    private String nickname;

    /**
     * access_token
     */
    @ApiModelProperty(value = "access_token")
    private String access_token;

    /**
     * 用户是否订阅该公众号标识 0-未订阅 1-已经订阅
     */
    @ApiModelProperty(value = "用户是否订阅该公众号标识 0-未订阅 1-已经订阅")
    private Integer subscribe;

    /**
     * 用户性别 1-女 2-男
     */
    @ApiModelProperty(value = "用户性别 1-女 2-男")
    private String sex;

    /**
     * 市
     */
    @ApiModelProperty(value = "市")
    private String city;

    /**
     * 省
     */
    @ApiModelProperty(value = "省")
    private String province;

    /**
     * 国籍
     */
    @ApiModelProperty(value = "国籍")
    private String country;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String headimgurl;

    /**
     * 与account关联ID
     */
    @ApiModelProperty("与account关联ID")
    private String accountUserId;

    /**
     * 认证类型 0、未认证 1、居民 2、居委会
     */
    @ApiModelProperty("认证类型 0、未认证 1、居民 2、居委会")
    private String identityType;

    /**
     * 街道ID
     */
    @ApiModelProperty("街道ID")
    private String streetId;

    /**
     * 社区ID
     */
    @ApiModelProperty("社区ID")
    private String communityId;

    /**
     * 小区ID
     */
    @ApiModelProperty("小区ID")
    private Long villageId;

    /**
     * 房屋ID
     */
    @ApiModelProperty("房屋ID")
    private Long buildingId;

    /**
     * 用户真实名称
     */
    @ApiModelProperty("用户真实名称")
    private String identityName;

    /**
     * 用户身份证号
     */
    @ApiModelProperty("用户身份证号")
    private String identityIdCard;

    /**
     * 用户手机号
     */
    @ApiModelProperty("用户手机号")
    private String identityMobile;

    /**
     * 人脸照片
     */
    @ApiModelProperty("人脸照片")
    private String identityFacePhotoIds;

    /**
     * 审核状态 1、驳回  2、待审批  3、已审核
     */
    @ApiModelProperty("审核状态 1、驳回  2、待审批  3、已审核")
    private String auditStatus;

    /**
     * 是否活跃状态 1-活跃 2-不活跃
     */
    @ApiModelProperty("是否活跃状态 1-活跃 2-不活跃")
    private String isActive;
}
