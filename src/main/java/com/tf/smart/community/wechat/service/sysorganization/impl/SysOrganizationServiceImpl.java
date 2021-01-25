package com.tf.smart.community.wechat.service.sysorganization.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tf.smart.community.wechat.common.utils.SessionUtils;
import com.tf.smart.community.wechat.entity.auth.UserDetail;
import com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationDTO;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationQueryDTO;
import com.tf.smart.community.wechat.dao.sysorganization.SysOrganizationMapper;
import com.tf.smart.community.wechat.service.sysorganization.ISysOrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationVO;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tf.smart.community.wechat.common.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 机构表 服务实现类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-25
 */
@Service
public class SysOrganizationServiceImpl extends ServiceImpl<SysOrganizationMapper, SysOrganization> implements ISysOrganizationService {

  @Autowired
  private SysOrganizationMapper sysOrganizationMapper;

  /**
   * 机构表列表
   * @Author LeeYoung
   * @Date 2021-01-25
   **/
  @Override
  public List<SysOrganizationVO> listNoPage(SysOrganizationQueryDTO param) {
    QueryWrapper<SysOrganization> queryWrapper = Wrappers.query();
    List<SysOrganization> sysOrganizationList = list(queryWrapper);
    List<SysOrganizationVO> sysOrganizationVOs = new ArrayList<>();
    sysOrganizationList.forEach(sysOrganization -> {
      SysOrganizationVO sysOrganizationVO = new SysOrganizationVO();
      BeanUtils.copyProperties(sysOrganization, sysOrganizationVO);
      sysOrganizationVOs.add(sysOrganizationVO);
    });
    return sysOrganizationVOs;
  }

  /**
   * 机构表列表(分页)
   * @Author LeeYoung
   * @Date 2021-01-25
   **/
  @Override
  public IPage<SysOrganizationVO> list(SysOrganizationQueryDTO param) {
    IPage<SysOrganization> page = new Page<>(param.getPage(),param.getSize());
    QueryWrapper<SysOrganization> queryWrapper = Wrappers.query();

    IPage<SysOrganization> pageList = page(page);
    List<SysOrganizationVO> sysOrganizationVOs = new ArrayList<>();
    pageList.getRecords().forEach(sysOrganization -> {
    SysOrganizationVO sysOrganizationVO = new SysOrganizationVO();
        BeanUtils.copyProperties(sysOrganization, sysOrganizationVO);
        sysOrganizationVOs.add(sysOrganizationVO);
    });

    IPage<SysOrganizationVO> responsePage = new Page<>();
    responsePage.setRecords(sysOrganizationVOs);
    responsePage.setCurrent(pageList.getCurrent());
    responsePage.setTotal(pageList.getTotal());
    responsePage.setPages(pageList.getPages());
    return responsePage;
  }

  /**
   * 机构表实体详情
   * @Author LeeYoung
   * @Date 2021-01-25
   **/
  @Override
  public SysOrganizationVO get(String id) {
    SysOrganization sysOrganization = getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(sysOrganization);
    SysOrganizationVO sysOrganizationVO = new SysOrganizationVO();
    BeanUtils.copyProperties(sysOrganization, sysOrganizationVO);
    return sysOrganizationVO ;
  }

  /**
  * 新增机构表实体
  * @Author LeeYoung
  * @Date 2021-01-25
  **/
  @Override
  public boolean save(SysOrganizationDTO param) {
//    SessionUtils.getUserDetail()
    SysOrganization sysOrganization = new SysOrganization();
    BeanUtils.copyProperties(param, sysOrganization);
    return save(sysOrganization);
  }


  /**
   * 修改机构表实体
   * @Author LeeYoung
   * @Date 2021-01-25
   **/
  @Override
  public boolean update(SysOrganizationDTO param) {
    SysOrganization sysOrganization = new SysOrganization();
    BeanUtils.copyProperties(param, sysOrganization);
    return updateById(sysOrganization);
  }


  /**
   * 删除机构表实体
   * @Author LeeYoung
   * @Date 2021-01-25
   **/
  @Override
  public boolean deleteByID(String id) {
    SysOrganization sysOrganization = this.getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(sysOrganization);
    return removeById(id);
  }
}
