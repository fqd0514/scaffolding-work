package com.tf.smart.community.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户详情
 *
 * @author 翟晶
 */
@ApiModel("用户详情")
@Data
public class UserDetail implements Serializable {
    @ApiModelProperty("用户主键")
    private String id;

    @ApiModelProperty("工号")
    private String code;

    @ApiModelProperty("姓名")
    private String username;

    @ApiModelProperty("真实姓名")
    private String realname;

    @ApiModelProperty("所属机构 ID")
    private String orgId;

    @ApiModelProperty("机构名称")
    private String orgName;

    @ApiModelProperty("是否删除：F-否、T-是")
    private String isDel;

    @ApiModelProperty("状态：F-挂起、T-启用")
    private String isEnabled;

    @ApiModelProperty("创建人")
    private String createUserid;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("机构")
    private Organization organization;

    @ApiModelProperty("角色列表")
    private List<Role> roleList;
}
