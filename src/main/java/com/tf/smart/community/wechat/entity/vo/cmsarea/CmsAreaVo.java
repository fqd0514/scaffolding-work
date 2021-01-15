package com.tf.smart.community.wechat.entity.vo.cmsarea;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tf.smart.community.wechat.entity.dto.base.PageDTO;
import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * <p>
 * CMS 区域表
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-15
 */
@Data
@ApiModel(value="CmsAreaVo对象", description="CMS 区域表")
public class CmsAreaVo {


    @ApiModelProperty(value = "区域_主键")
    @Excel(name = "区域_主键", orderNum = "0", width = 30)
    private Long areaId;

    @ApiModelProperty(value = "区域_类型(字典表)：1-室内、2-室外")
    @Excel(name = "区域_类型(字典表)：1-室内、2-室外", orderNum = "1", width = 30)
    private String areaType;

    @ApiModelProperty(value = "区域_位置：1#~12#-室内、1#~7#-室外")
    @Excel(name = "区域_位置：1#~12#-室内、1#~7#-室外", orderNum = "2", width = 30)
    private String areaSite;

    @ApiModelProperty(value = "区域_排序")
    @Excel(name = "区域_排序", orderNum = "3", width = 30)
    private Integer areaSort;

    @ApiModelProperty(value = "区域_状态：1-启用、2-停用")
    @Excel(name = "区域_状态：1-启用、2-停用", orderNum = "4", width = 30)
    private String areaStatus;

    @ApiModelProperty(value = "设备_编码")
    @Excel(name = "设备_编码", orderNum = "5", width = 30)
    private String deviceCode;


}
