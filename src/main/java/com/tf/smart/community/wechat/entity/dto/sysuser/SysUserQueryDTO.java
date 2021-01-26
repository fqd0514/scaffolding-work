package com.tf.smart.community.wechat.entity.dto.sysuser;

import java.time.LocalDateTime;
import com.tf.smart.community.wechat.entity.dto.base.PageDTO;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysUserQuery对象", description="用户表")
public class SysUserQueryDTO extends PageDTO {


    @ApiModelProperty(value = "用户ID")
    private String id;

    @ApiModelProperty(value = "工号")
    private String code;

    @ApiModelProperty(value = "姓名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    private String password;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "所属机构ID")
    private String orgId;

    @ApiModelProperty(value = "身份证号")
    private String idcard;

    @ApiModelProperty(value = "手机")
    private String telphone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "办公电话")
    private String phoneOffice;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "头像")
    private String photo;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "是否删除 0-未删除 1-已删除")
    private String isDel;

    @ApiModelProperty(value = "创建人")
    private String createUserid;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后操作人")
    private String updateUserId;

    @ApiModelProperty(value = "最后操作时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "状态 0-启用 1-挂起")
    private String isEnabled;


}
