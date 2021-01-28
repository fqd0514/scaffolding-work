package com.tf.smart.community.wechat.entity.vo.sysorganization;

import com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 机构树 VO
 * @Author Leeyoung
 * @Date 2021/1/27
 **/
@ApiModel("机构树 VO")
@Data
public class SysOrganizationTreeVO extends SysOrganization {

    /**
     * 子机构详情
     */
    @ApiModelProperty("子机构详情")
    private List<SysOrganizationVO> children;
}
