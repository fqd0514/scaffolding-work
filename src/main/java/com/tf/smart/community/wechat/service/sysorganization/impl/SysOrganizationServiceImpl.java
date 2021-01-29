package com.tf.smart.community.wechat.service.sysorganization.impl;

import com.tf.smart.community.wechat.common.constant.CommonConstant;
import com.tf.smart.community.wechat.common.enums.DeleteEnum;
import com.tf.smart.community.wechat.common.utils.UuidUtil;
import com.tf.smart.community.wechat.entity.po.sysorganization.SysOrganization;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationDTO;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationQueryDTO;
import com.tf.smart.community.wechat.dao.sysorganization.SysOrganizationMapper;
import com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationTreeVO;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

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
    //todo 登录写完后写
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
    sysOrganization.setIsDel(DeleteEnum.IS_NOT_DELETE.getCode());

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
   * 获取机构树
   * @param sysOrganizationDTO 查询条件
   * @return java.util.List<com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationVO>
   * @Author Leeyoung
   * @Date 2021/1/27
   **/
  @Override
  public List<SysOrganizationTreeVO> tree(SysOrganizationDTO sysOrganizationDTO) {
    List<SysOrganizationTreeVO> sysOrganizationTreeVoList = new ArrayList<>();

    QueryWrapper<SysOrganization> queryWrapper = Wrappers.query();
    queryWrapper.eq("is_del",DeleteEnum.IS_NOT_DELETE.getCode());
    List<SysOrganization> sysOrganizationList = list(queryWrapper);

    if (ObjectUtils.isEmpty(sysOrganizationList)) {
      return sysOrganizationTreeVoList;
    }

    //先根据等级分组
    Map<String, List<SysOrganization>> orgLevelMap = sysOrganizationList.stream().collect(Collectors.groupingBy(SysOrganization::getLevel));

    List<SysOrganizationVO> sysOrganizationVOS = treeRecursion(orgLevelMap, Integer.valueOf(CommonConstant.LEVEL_TOP));

    sysOrganizationVOS.forEach(org->{
      SysOrganizationTreeVO sysOrganizationTreeVO = new SysOrganizationTreeVO();
      BeanUtils.copyProperties(org,sysOrganizationTreeVO);
      sysOrganizationTreeVoList.add(sysOrganizationTreeVO);
    });

    return sysOrganizationTreeVoList;
  }

  public List<SysOrganizationVO> treeRecursion(Map<String, List<SysOrganization>> orgLevelMap,Integer level){

    if(!ObjectUtils.isEmpty(orgLevelMap.get(String.valueOf(level+Integer.valueOf(CommonConstant.LEVEL_TOP))))){

      //递归向下查找
      List<SysOrganizationVO> treeList = treeRecursion(orgLevelMap, level+Integer.valueOf(CommonConstant.LEVEL_TOP));

      if(level.equals(Integer.valueOf(CommonConstant.LEVEL_TOP))){
        return treeList;
      }
      List<SysOrganizationVO> fatherTree = getFatherTree(orgLevelMap, level,treeList);
      return fatherTree;

    }else {
      //算出最后一层和倒数第二层的结果
      List<SysOrganizationVO> fatherTree = getFatherTree(orgLevelMap, level,null);
      return fatherTree;
    }
  }


  private List<SysOrganizationVO> getFatherTree(Map<String, List<SysOrganization>> orgLevelMap,Integer level,List<SysOrganizationVO> subTreeList){
    List<SysOrganizationVO> treeListReturn = new ArrayList<>();

    //子
    List<SysOrganization> subAllOrgList = orgLevelMap.get(String.valueOf(level));
    Map<String, List<SysOrganization>> subOrgByPidMap = subAllOrgList.stream().collect(Collectors.groupingBy(SysOrganization::getPid));

    //父
    List<SysOrganization> fatherAllOrgList = orgLevelMap.get(String.valueOf(level - Integer.valueOf(CommonConstant.LEVEL_TOP)));

    //已经存在子集
    if(!ObjectUtils.isEmpty(subTreeList)){
      Map<String, List<SysOrganizationVO>> alreadySubMap = subTreeList.stream().collect(Collectors.groupingBy(SysOrganizationVO::getPid));

      //赋值
      for (SysOrganization sysOrganization : fatherAllOrgList) {
        SysOrganizationVO sysOrganizationTreeVO = new SysOrganizationVO();
        List<SysOrganization> sysOrganizations = subOrgByPidMap.get(sysOrganization.getId());
        BeanUtils.copyProperties(sysOrganization,sysOrganizationTreeVO);

        if(ObjectUtils.isEmpty(sysOrganizations)){
          treeListReturn.add(sysOrganizationTreeVO);
          continue;
        }

        List<SysOrganizationVO> sysOrganizationVOS = new ArrayList<>(sysOrganizations.size());
        sysOrganizations.forEach(org->{
          SysOrganizationVO sysOrganizationVO = new SysOrganizationVO();
          BeanUtils.copyProperties(org,sysOrganizationVO);
          sysOrganizationVOS.add(sysOrganizationVO);
        });

        sysOrganizationTreeVO.setChildren(alreadySubMap.get(sysOrganization.getId()));
        treeListReturn.add(sysOrganizationTreeVO);
      }
    }else {
      //赋值
      for (SysOrganization sysOrganization : fatherAllOrgList) {
        List<SysOrganization> sysOrganizations = subOrgByPidMap.get(sysOrganization.getId());
        SysOrganizationVO sysOrganizationTreeVO = new SysOrganizationVO();
        BeanUtils.copyProperties(sysOrganization,sysOrganizationTreeVO);
        if(ObjectUtils.isEmpty(sysOrganizations)){
          treeListReturn.add(sysOrganizationTreeVO);
          continue;
        }
        List<SysOrganizationVO> sysOrganizationVOS = new ArrayList<>(sysOrganizations.size());
        sysOrganizations.forEach(org->{
          SysOrganizationVO sysOrganizationVO = new SysOrganizationVO();
          BeanUtils.copyProperties(org,sysOrganizationVO);
          sysOrganizationVOS.add(sysOrganizationVO);
        });

        sysOrganizationTreeVO.setChildren(sysOrganizationVOS);

        treeListReturn.add(sysOrganizationTreeVO);
      }

    }
      return treeListReturn;
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
