package com.tf.smart.community.wechat.service.sysorganization.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.tf.smart.community.wechat.common.constant.CommonConstant;
import com.tf.smart.community.wechat.common.utils.SessionUtils;
import com.tf.smart.community.wechat.common.utils.UuidUtil;
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
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    if (param.getPid().equals(CommonConstant.ZERO_STR)) {
      throw new CommonBusinessException(CommonResponseEnum.INVALID_PARAMTER.getCode(), "不允许手动添加顶级机构");
    }

    //获取父机构
    SysOrganization parent = existsOrganization(param.getPid());
    int level = Integer.valueOf(parent.getLevel()) + 1;

    //机构类型校验个数限制
    if (sysOrganizationMapper.countByName(param.getName()) > CommonConstant.ZERO_INT) {
      throw new CommonBusinessException(CommonResponseEnum.SYSTEM_ERROR.getCode(), "机构名称不能重复");
    }

    SysOrganization sysOrganization = new SysOrganization();
    BeanUtils.copyProperties(param, sysOrganization);
    String id = UuidUtil.getUUID32();
    sysOrganization.setId(id);
    sysOrganization.setLevel(String.valueOf(level));
    sysOrganization.setIsDel("F");

    //todo  登录做完后修改
    sysOrganization.setCreateUserId("1");
    sysOrganization.setCreateTime(new Date());

    //设置所属父级ID
    sysOrganization.setOrganizationIds((StringUtils.isEmpty(parent.getOrganizationIds())?
            parent.getId():parent.getOrganizationIds())+","+sysOrganization.getId());

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

  /**
   * 判断机构是否存在
   * @param id
   * @return com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization
   * @Author Leeyoung
   * @Date 2021/1/27
   **/
  private SysOrganization existsOrganization(String id) {
    SysOrganization sysOrganization = sysOrganizationMapper.selectById(id);
    if (Objects.isNull(sysOrganization) || sysOrganization.getIsDel().equals("T")) {
      throw new CommonBusinessException(CommonResponseEnum.SYSTEM_ERROR.getCode(), "机构不存在");
    }

    return sysOrganization;
  }
}
