package com.tf.smart.community.wechat.service.sysuser.impl;

import com.tf.smart.community.wechat.entity.po.sysuser.SysUser;
import com.tf.smart.community.wechat.entity.dto.sysuser.SysUserDTO;
import com.tf.smart.community.wechat.entity.dto.sysuser.SysUserQueryDTO;
import com.tf.smart.community.wechat.dao.sysuser.SysUserMapper;
import com.tf.smart.community.wechat.service.sysuser.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tf.smart.community.wechat.entity.vo.sysuser.SysUserVO;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tf.smart.community.wechat.common.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

  /**
   * 用户表列表
   * @Author LeeYoung
   * @Date 2021-01-26
   **/
  @Override
  public List<SysUserVO> listNoPage(SysUserQueryDTO param) {
    QueryWrapper<SysUser> queryWrapper = Wrappers.query();
    List<SysUser> sysUserList = list(queryWrapper);
    List<SysUserVO> sysUserVOs = new ArrayList<>();
    sysUserList.forEach(sysUser -> {
      SysUserVO sysUserVO = new SysUserVO();
      BeanUtils.copyProperties(sysUser, sysUserVO);
      sysUserVOs.add(sysUserVO);
    });
    return sysUserVOs;
  }

  /**
   * 用户表列表(分页)
   * @Author LeeYoung
   * @Date 2021-01-26
   **/
  @Override
  public IPage<SysUserVO> list(SysUserQueryDTO param) {
    IPage<SysUser> page = new Page<>(param.getPage(),param.getSize());
    QueryWrapper<SysUser> queryWrapper = Wrappers.query();
    IPage<SysUser> pageList = page(page);
    List<SysUserVO> sysUserVOs = new ArrayList<>();
    pageList.getRecords().forEach(sysUser -> {
    SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser, sysUserVO);
        sysUserVOs.add(sysUserVO);
    });

    IPage<SysUserVO> responsePage = new Page<>();
    responsePage.setRecords(sysUserVOs);
    responsePage.setCurrent(pageList.getCurrent());
    responsePage.setTotal(pageList.getTotal());
    responsePage.setPages(pageList.getPages());
    return responsePage;
  }

  /**
   * 用户表实体详情
   * @Author LeeYoung
   * @Date 2021-01-26
   **/
  @Override
  public SysUserVO get(String id) {
    SysUser sysUser = getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(sysUser);
    SysUserVO sysUserVO = new SysUserVO();
    BeanUtils.copyProperties(sysUser, sysUserVO);
    return sysUserVO ;
  }

  /**
  * 新增用户表实体
  * @Author LeeYoung
  * @Date 2021-01-26
  **/
  @Override
  public boolean save(SysUserDTO param) {
    SysUser sysUser = new SysUser();
    BeanUtils.copyProperties(param, sysUser);
    return save(sysUser);
  }


  /**
   * 修改用户表实体
   * @Author LeeYoung
   * @Date 2021-01-26
   **/
  @Override
  public boolean update(SysUserDTO param) {
    SysUser sysUser = new SysUser();
    BeanUtils.copyProperties(param, sysUser);
    return updateById(sysUser);
  }


  /**
   * 删除用户表实体
   * @Author LeeYoung
   * @Date 2021-01-26
   **/
  @Override
  public boolean deleteByID(String id) {
    SysUser sysUser = this.getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(sysUser);
    return removeById(id);
  }
}
