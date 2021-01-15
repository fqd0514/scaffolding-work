package com.tf.smart.community.wechat.entity.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 机构
 *
 * @author 翟晶
 */
@ApiModel("机构")
@Data
public class Organization implements Serializable {
    @ApiModelProperty("机构主键")
    private String id;

    @ApiModelProperty("机构名称")
    private String name;

    @ApiModelProperty("父机构主键")
    private String pid;

    @ApiModelProperty("机构级别")
    private String level;

    @ApiModelProperty("归属区域")
    private String address;
}
