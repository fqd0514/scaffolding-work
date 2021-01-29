package com.tf.smart.community.wechat.entity.vo.sysorganization;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tf.smart.community.wechat.entity.TreeNode;
import com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization;
import com.tf.smart.community.wechat.service.PojoConvertor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 机构树 VO
 * @Author Leeyoung
 * @Date 2021/1/27
 **/
@ApiModel("机构树 VO")
@Data
public class SysOrganizationTreeVO extends TreeNode{

    @ApiModelProperty(value = "机构ID")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "机构名称")
    private String name;

    @ApiModelProperty(value = "父机构ID")
    private String pid;

    @ApiModelProperty(value = "机构级别")
    private String level;

    @ApiModelProperty(value = "归属区域")
    private String address;

    @ApiModelProperty(value = "是否删除 0-未删除  1-已删除")
    private String isDel;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改人id")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUserId;

    @ApiModelProperty(value = "最后操作时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "所属机构ID，以逗号分隔")
    private String organizationIds;
}
