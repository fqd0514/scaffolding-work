package com.tf.smart.community.wechat.entity.po.cmsarea;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tf.smart.community.wechat.entity.dto.base.PageDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

/**
 * <p>
 * CMS 区域表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-15
 */
@Data
@ApiModel(value="CmsArea对象", description="CMS 区域表")
public class CmsArea {


    @ApiModelProperty(value = "区域_主键")
    @TableId(value = "area_id", type = IdType.AUTO)
    private Long areaId;

    @ApiModelProperty(value = "区域_类型(字典表)：1-室内、2-室外")
    private String areaType;

    @ApiModelProperty(value = "区域_位置：1#~12#-室内、1#~7#-室外")
    private String areaSite;

    @ApiModelProperty(value = "区域_排序")
    private Integer areaSort;

    @ApiModelProperty(value = "区域_状态：1-启用、2-停用")
    private String areaStatus;

    @ApiModelProperty(value = "设备_编码")
    private String deviceCode;


}
