package com.tf.smart.community.wechat.entity.vo.sysuser;

import java.time.LocalDateTime;
import com.tf.smart.community.wechat.entity.dto.base.PageDTO;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * <p>
 * 用户表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-26
 */
@Data
@ApiModel(value="SysUserVO对象", description="用户表")
public class SysUserVO {


    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID", orderNum = "0", width = 30)
    private String id;

    @ApiModelProperty(value = "工号")
    @Excel(name = "工号", orderNum = "1", width = 30)
    private String code;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名", orderNum = "2", width = 30)
    private String username;

    @ApiModelProperty(value = "用户密码")
    @Excel(name = "用户密码", orderNum = "3", width = 30)
    private String password;

    @ApiModelProperty(value = "密码盐")
    @Excel(name = "密码盐", orderNum = "4", width = 30)
    private String salt;

    @ApiModelProperty(value = "所属机构ID")
    @Excel(name = "所属机构ID", orderNum = "5", width = 30)
    private String orgId;

    @ApiModelProperty(value = "身份证号")
    @Excel(name = "身份证号", orderNum = "6", width = 30)
    private String idcard;

    @ApiModelProperty(value = "手机")
    @Excel(name = "手机", orderNum = "7", width = 30)
    private String telphone;

    @ApiModelProperty(value = "邮箱")
    @Excel(name = "邮箱", orderNum = "8", width = 30)
    private String email;

    @ApiModelProperty(value = "办公电话")
    @Excel(name = "办公电话", orderNum = "9", width = 30)
    private String phoneOffice;

    @ApiModelProperty(value = "真实姓名")
    @Excel(name = "真实姓名", orderNum = "10", width = 30)
    private String realname;

    @ApiModelProperty(value = "头像")
    @Excel(name = "头像", orderNum = "11", width = 30)
    private String photo;

    @ApiModelProperty(value = "性别")
    @Excel(name = "性别", orderNum = "12", width = 30)
    private String sex;

    @ApiModelProperty(value = "是否删除 0-未删除 1-已删除")
    @Excel(name = "是否删除 0-未删除 1-已删除", orderNum = "13", width = 30)
    private String isDel;

    @ApiModelProperty(value = "创建人")
    @Excel(name = "创建人", orderNum = "14", width = 30)
    private String createUserid;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", orderNum = "15", width = 30,exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后操作人")
    @Excel(name = "最后操作人", orderNum = "16", width = 30)
    private String updateUserId;

    @ApiModelProperty(value = "最后操作时间")
    @Excel(name = "最后操作时间", orderNum = "17", width = 30,exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注", orderNum = "18", width = 30)
    private String remark;

    @ApiModelProperty(value = "状态 0-启用 1-挂起")
    @Excel(name = "状态 0-启用 1-挂起", orderNum = "19", width = 30)
    private String isEnabled;


}
