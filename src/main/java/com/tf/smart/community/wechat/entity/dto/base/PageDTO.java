package com.tf.smart.community.wechat.entity.dto.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 分页 DTO
 *
 * @author 翟晶
 */
@ApiModel(value = "分页 DTO", description = "分页 DTO")
@Data
@ToString
public class PageDTO implements Serializable {
    @ApiModelProperty(value = "当前页数", dataType = "Integer")
    @Min(value = 1, message = "当前页数最小是1")
    private Integer page = 1;

    @ApiModelProperty(value = "每页条数", dataType = "Integer")
    @Min(value = 1, message = "每页条数最小是1")
    private Integer size = 10;
}
