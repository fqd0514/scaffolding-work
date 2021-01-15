package com.tf.smart.community.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 *
 * @author 翟晶
 */
@ApiModel("角色")
@Data
public class Role implements Serializable {
    @ApiModelProperty("角色主键")
    private String id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("是否为管理员：T-是、F-否")
    private String isAdmin;

    @ApiModelProperty("岗位职责")
    private String work;

    @ApiModelProperty("是否删除：F-否、T-是")
    private String isDel;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态：F-挂起、T-启用")
    private String isEnabled;

    @ApiModelProperty("创建人")
    private String createUserid;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
