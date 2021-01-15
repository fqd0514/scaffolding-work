package com.tf.smart.community.wechat.service.cmsarea.impl;

import com.tf.smart.community.wechat.entity.po.cmsarea.CmsArea;
import com.tf.smart.community.wechat.entity.dto.cmsarea.CmsAreaDto;
import com.tf.smart.community.wechat.entity.query.cmsarea.CmsAreaQuery;
import com.tf.smart.community.wechat.dao.cmsarea.CmsAreaMapper;
import com.tf.smart.community.wechat.service.cmsarea.ICmsAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tf.smart.community.wechat.entity.vo.cmsarea.CmsAreaVo;
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
 * CMS 区域表 服务实现类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-15
 */
@Service
public class CmsAreaServiceImpl extends ServiceImpl<CmsAreaMapper, CmsArea> implements ICmsAreaService {

  @Override
  public List<CmsAreaVo> listNoPage(CmsAreaQuery param) {
    QueryWrapper<CmsArea> queryWrapper = Wrappers.query();
    List<CmsArea> cmsAreaList = list(queryWrapper);
    List<CmsAreaVo> cmsAreaVos = new ArrayList<>();
    cmsAreaList.forEach(cmsArea -> {
      CmsAreaVo cmsAreaVo = new CmsAreaVo();
      BeanUtils.copyProperties(cmsArea, cmsAreaVo);
      cmsAreaVos.add(cmsAreaVo);
    });
    return cmsAreaVos;
  }

  @Override
  public IPage<CmsAreaVo> list(CmsAreaQuery param) {
    IPage<CmsArea> page = new Page<>(param.getPage(),param.getSize());
    QueryWrapper<CmsArea> queryWrapper = Wrappers.query();
    IPage<CmsArea> pageList = page(page);
    List<CmsAreaVo> cmsAreaVos = new ArrayList<>();
    pageList.getRecords().forEach(cmsArea -> {
    CmsAreaVo cmsAreaVo = new CmsAreaVo();
        BeanUtils.copyProperties(cmsArea, cmsAreaVo);
        cmsAreaVos.add(cmsAreaVo);
    });

    IPage<CmsAreaVo> responsePage = new Page<>();
    responsePage.setRecords(cmsAreaVos);
    responsePage.setCurrent(pageList.getCurrent());
    responsePage.setTotal(pageList.getTotal());
    responsePage.setPages(pageList.getPages());
    return responsePage;
  }

  @Override
  public CmsAreaVo get(String id) {
    CmsArea cmsArea = getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(cmsArea);
    CmsAreaVo cmsAreaVo = new CmsAreaVo();
    BeanUtils.copyProperties(cmsArea, cmsAreaVo);
    return cmsAreaVo ;
  }

  @Override
  public boolean save(CmsAreaDto param) {
    CmsArea cmsArea = new CmsArea();
    BeanUtils.copyProperties(param, cmsArea);
    return save(cmsArea);
  }

  @Override
  public boolean update(CmsAreaDto param) {
    CmsArea cmsArea = new CmsArea();
    BeanUtils.copyProperties(param, cmsArea);
    return updateById(cmsArea);
  }

  @Override
  public boolean deleteByID(String id) {
    CmsArea cmsArea = this.getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(cmsArea);
    return removeById(id);
  }
}
