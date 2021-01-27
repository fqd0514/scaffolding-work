package com.tf.smart.community.wechat.dao.sysorganization;

import com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 机构表 Mapper 接口
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-25
 */
public interface SysOrganizationMapper extends BaseMapper<SysOrganization> {

    /**
     * 根据名称计算机构数量
     * @param name 机构名称
     * @return int
     * @Author Leeyoung
     * @Date 2021/1/27
     **/
    int countByName(@Param("name") String name);
}
