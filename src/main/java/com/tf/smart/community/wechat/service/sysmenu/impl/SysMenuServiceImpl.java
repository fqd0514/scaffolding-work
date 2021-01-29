package com.tf.smart.community.wechat.service.sysmenu.impl;

import com.tf.smart.community.wechat.entity.po.sysmenu.SysMenu;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuDTO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuUpdateDTO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuQueryDTO;
import com.tf.smart.community.wechat.dao.sysmenu.SysMenuMapper;
import com.tf.smart.community.wechat.service.sysmenu.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tf.smart.community.wechat.entity.vo.sysmenu.SysMenuVO;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 权限定义表 服务实现类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-29
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

  /**
   * 权限定义表列表
   * @Author LeeYoung
   * @Date 2021-01-29
   **/
  @Override
  public List<SysMenuVO> listNoPage(SysMenuQueryDTO param) {
    QueryWrapper<SysMenu> queryWrapper = Wrappers.query();
    List<SysMenu> sysMenuList = list(queryWrapper);
    List<SysMenuVO> sysMenuVOs = new ArrayList<>();
    sysMenuList.forEach(sysMenu -> {
      SysMenuVO sysMenuVO = new SysMenuVO();
      BeanUtils.copyProperties(sysMenu, sysMenuVO);
      sysMenuVOs.add(sysMenuVO);
    });
    return sysMenuVOs;
  }

  /**
   * 权限定义表列表(分页)
   * @Author LeeYoung
   * @Date 2021-01-29
   **/
  @Override
  public IPage<SysMenuVO> list(SysMenuQueryDTO param) {
    IPage<SysMenu> page = new Page<>(param.getPage(),param.getSize());
    QueryWrapper<SysMenu> queryWrapper = Wrappers.query();
    IPage<SysMenu> pageList = page(page);
    List<SysMenuVO> sysMenuVOs = new ArrayList<>();
    pageList.getRecords().forEach(sysMenu -> {
    SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(sysMenu, sysMenuVO);
        sysMenuVOs.add(sysMenuVO);
    });

    IPage<SysMenuVO> responsePage = new Page<>();
    responsePage.setRecords(sysMenuVOs);
    responsePage.setCurrent(pageList.getCurrent());
    responsePage.setTotal(pageList.getTotal());
    responsePage.setPages(pageList.getPages());
    return responsePage;
  }

  /**
   * 权限定义表实体详情
   * @Author LeeYoung
   * @Date 2021-01-29
   **/
  @Override
  public SysMenuVO get(String id) {
    SysMenu sysMenu = getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(sysMenu);
    SysMenuVO sysMenuVO = new SysMenuVO();
    BeanUtils.copyProperties(sysMenu, sysMenuVO);
    return sysMenuVO ;
  }

  /**
  * 新增权限定义表实体
  * @Author LeeYoung
  * @Date 2021-01-29
  **/
  @Override
  public boolean save(SysMenuDTO param) {
    SysMenu sysMenu = new SysMenu();
    BeanUtils.copyProperties(param, sysMenu);
    return save(sysMenu);
  }


  /**
   * 修改权限定义表实体
   * @Author LeeYoung
   * @Date 2021-01-29
   **/
  @Override
  public boolean update(SysMenuUpdateDTO param) {
    SysMenu sysMenu = new SysMenu();
    BeanUtils.copyProperties(param, sysMenu);
    return updateById(sysMenu);
  }


  /**
   * 删除权限定义表实体
   * @Author LeeYoung
   * @Date 2021-01-29
   **/
  @Override
  public boolean deleteByID(String id) {
    SysMenu sysMenu = this.getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(sysMenu);
    return removeById(id);
  }
}
