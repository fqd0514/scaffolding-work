package com.tf.smart.community.wechat.service.sysorganization;

import com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization;
import com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationTreeVO;
import com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationVO;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationDTO;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * <p>
 * 机构表 服务类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-25
 */
public interface ISysOrganizationService extends IService<SysOrganization> {


    /**
    * 获取实体list
    *
    * @return
    */
    List<SysOrganizationVO> listNoPage(SysOrganizationQueryDTO param);

    /**
    * 获取实体list分页
    *
    * @param param
    * @return
    */
    IPage<SysOrganizationVO> list(SysOrganizationQueryDTO param);

    /**
    * 获取实体详情
    *
    * @param id
    * @return
    */
    SysOrganizationVO get(String id);

    /**
    * 保存实体
    *
    * @param param
    * @return
    */
    boolean save(SysOrganizationDTO param);

    /**
    * 更新实体
    *
    * @param param
    * @return
    */
    boolean update(SysOrganizationDTO param);

    /**
    * 根据ID删除
    *
    * @param id
    * @return
    */
    boolean deleteByID(String id);

    /**
     * 机构树
     * @param  sysOrganizationDTO 查询条件
     * @return java.util.List<SysOrganizationTreeVo>
     * @Author Leeyoung
     * @Date 2021/1/27
     **/
    List<SysOrganizationTreeVO> tree(SysOrganizationDTO sysOrganizationDTO);
}
