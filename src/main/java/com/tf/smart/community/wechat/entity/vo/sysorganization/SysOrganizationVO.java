package com.tf.smart.community.wechat.entity.vo.sysorganization;

import java.time.LocalDateTime;
import java.util.List;

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
 * 机构表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-25
 */
@Data
@ApiModel(value="SysOrganizationVO对象", description="机构表")
public class SysOrganizationVO {


    @ApiModelProperty(value = "机构ID")
    @Excel(name = "机构ID", orderNum = "0", width = 30)
    private String id;

    @ApiModelProperty(value = "机构名称")
    @Excel(name = "机构名称", orderNum = "1", width = 30)
    private String name;

    @ApiModelProperty(value = "父机构ID")
    @Excel(name = "父机构ID", orderNum = "2", width = 30)
    private String pid;

    @ApiModelProperty(value = "机构级别")
    @Excel(name = "机构级别", orderNum = "3", width = 30)
    private String level;

    @ApiModelProperty(value = "归属区域")
    @Excel(name = "归属区域", orderNum = "4", width = 30)
    private String address;

    @ApiModelProperty(value = "是否删除 0-未删除  1-已删除")
    @Excel(name = "是否删除 0-未删除  1-已删除", orderNum = "5", width = 30)
    private String isDel;

    @ApiModelProperty(value = "创建人")
    @Excel(name = "创建人", orderNum = "6", width = 30)
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    @Excel(name = "创建时间", orderNum = "7", width = 30,exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改人id")
    @Excel(name = "修改人id", orderNum = "8", width = 30)
    private String updateUserId;

    @ApiModelProperty(value = "最后操作时间")
    @Excel(name = "最后操作时间", orderNum = "9", width = 30,exportFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注", orderNum = "10", width = 30)
    private String remark;

    @ApiModelProperty(value = "所属机构ID，以逗号分隔")
    @Excel(name = "所属机构ID，以逗号分隔", orderNum = "11", width = 30)
    private String organizationIds;

    @ApiModelProperty(value = "子机构详情")
    private List<SysOrganizationVO> children;

}
