package com.tf.smart.community.wechat.entity.dto.sysmenu;

import java.time.LocalDateTime;
import com.tf.smart.community.wechat.entity.dto.base.PageDTO;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限定义表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="SysMenuQuery对象", description="权限定义表")
public class SysMenuQueryDTO extends PageDTO {


    @ApiModelProperty(value = "菜单ID")
    private String id;

    @ApiModelProperty(value = "菜单名称")
    private String name;

    @ApiModelProperty(value = "R-根节点(系统名称)、0-主菜单、1-非主菜单")
    private String isRoot;

    @ApiModelProperty(value = "父节点ID")
    private String parentId;

    @ApiModelProperty(value = "级别")
    private String level;

    @ApiModelProperty(value = "序号")
    private Integer orderNum;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

    @ApiModelProperty(value = "权限类型 0001-目录、1001-菜单、2001-功能")
    private String powerType;

    @ApiModelProperty(value = "功能类型")
    private String functionType;

    @ApiModelProperty(value = "链接类型 1-内部链接、2-外部链接")
    private String linkType;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    private String createUserId;

    @ApiModelProperty(value = "最后操作时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后操作人")
    private String updateUserId;

    @ApiModelProperty(value = "状态 0-未删除、1-已删除")
    private String isDel;

    @ApiModelProperty(value = "所属平台ID")
    private String platformId;

    @ApiModelProperty(value = "权限定义编码")
    private String permissionCode;

    @ApiModelProperty(value = "显示方式 1-横向、2-纵向")
    private String displayMode;


}
