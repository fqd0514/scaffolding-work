package com.tf.smart.community.wechat.entity.dto.question;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/***
 * 调查问卷人员和题目答案DTO
 * @Author Leeyoung
 * @Date 2020/8/13
 **/
@ApiModel(value = "调查问卷人员和题目答案DTO", description = "调查问卷人员和题目答案DTO")
@Data
public class AccountAnswerRelationDTO {

    /**
     * 主键ID
     */
    @JsonIgnore
    private Long id;

    /**
     * 微信认证表的ID
     */
    @JsonIgnore
    private String openId;

    /**
     * 楼栋ID
     */
    @JsonIgnore
    private Long buildingId;

    /**
     * 问卷主键
     */
    @ApiModelProperty(value = "问卷主键", dataType = "Long")
    @NotNull(message = "问卷主键不能为空")
    private Long questionId;

}