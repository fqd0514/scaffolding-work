package com.tf.smart.community.wechat.entity.vo.sysmenu;

import java.time.LocalDateTime;
import com.tf.smart.community.wechat.entity.dto.base.PageDTO;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * <p>
 * 权限定义表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-29
 */
@Data
@ApiModel(value="SysMenuVO对象", description="权限定义表")
public class SysMenuVO {


    @ApiModelProperty(value = "菜单ID")
    @Excel(name = "菜单ID", orderNum = "0", width = 30)
    private String id;

    @ApiModelProperty(value = "菜单名称")
    @Excel(name = "菜单名称", orderNum = "1", width = 30)
    private String name;

    @ApiModelProperty(value = "R-根节点(系统名称)、0-主菜单、1-非主菜单")
    @Excel(name = "R-根节点(系统名称)、0-主菜单、1-非主菜单", orderNum = "2", width = 30)
    private String isRoot;

    @ApiModelProperty(value = "父节点ID")
    @Excel(name = "父节点ID", orderNum = "3", width = 30)
    private String parentId;

    @ApiModelProperty(value = "级别")
    @Excel(name = "级别", orderNum = "4", width = 30)
    private String level;

    @ApiModelProperty(value = "序号")
    @Excel(name = "序号", orderNum = "5", width = 30)
    private Integer orderNum;

    @ApiModelProperty(value = "链接地址")
    @Excel(name = "链接地址", orderNum = "6", width = 30)
    private String url;

    @ApiModelProperty(value = "菜单图标")
    @Excel(name = "菜单图标", orderNum = "7", width = 30)
    private String icon;

    @ApiModelProperty(value = "权限类型 0001-目录、1001-菜单、2001-功能")
    @Excel(name = "权限类型 0001-目录、1001-菜单、2001-功能", orderNum = "8", width = 30)
    private String powerType;

    @ApiModelProperty(value = "功能类型")
    @Excel(name = "功能类型", orderNum = "9", width = 30)
    private String functionType;

    @ApiModelProperty(value = "链接类型 1-内部链接、2-外部链接")
    @Excel(name = "链接类型 1-内部链接、2-外部链接", orderNum = "10", width = 30)
    private String linkType;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", orderNum = "11", width = 30,exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建人")
    @Excel(name = "创建人", orderNum = "12", width = 30)
    private String createUserId;

    @ApiModelProperty(value = "最后操作时间")
    @Excel(name = "最后操作时间", orderNum = "13", width = 30,exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "最后操作人")
    @Excel(name = "最后操作人", orderNum = "14", width = 30)
    private String updateUserId;

    @ApiModelProperty(value = "状态 0-未删除、1-已删除")
    @Excel(name = "状态 0-未删除、1-已删除", orderNum = "15", width = 30)
    private String isDel;

    @ApiModelProperty(value = "所属平台ID")
    @Excel(name = "所属平台ID", orderNum = "16", width = 30)
    private String platformId;

    @ApiModelProperty(value = "权限定义编码")
    @Excel(name = "权限定义编码", orderNum = "17", width = 30)
    private String permissionCode;

    @ApiModelProperty(value = "显示方式 1-横向、2-纵向")
    @Excel(name = "显示方式 1-横向、2-纵向", orderNum = "18", width = 30)
    private String displayMode;


}
