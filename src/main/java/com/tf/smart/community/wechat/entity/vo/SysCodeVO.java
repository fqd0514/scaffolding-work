package com.tf.smart.community.wechat.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 系统编码 VO
 *
 * @author 翟晶
 */
@ApiModel(value = "系统编码 VO", description = "系统编码 VO")
@Data
@ToString
public class SysCodeVO {
    @ApiModelProperty("编码名称")
    private String codeName;

    @ApiModelProperty("编码标签")
    private String codeLabel;

    @ApiModelProperty("编码值")
    private String codeValue;

    @ApiModelProperty("编码排序")
    private Byte codeSort;
}
