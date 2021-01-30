package com.tf.smart.community.wechat.service.sysorganization.impl;

import com.tf.smart.community.wechat.common.constant.CommonConstant;
import com.tf.smart.community.wechat.common.enums.DeleteEnum;
import com.tf.smart.community.wechat.common.utils.List2Tree;
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

import java.time.LocalDateTime;
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
    sysOrganization.setCreateTime(LocalDateTime.now());

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
    SysOrganization sysOrganization = new SysOrganization();
    BeanUtils.copyProperties(sysOrganizationDTO,sysOrganization);

    //获取全部节点
    QueryWrapper<SysOrganization> queryWrapper = Wrappers.query();
    List<SysOrganization> sysOrganizationList = list(queryWrapper);

    if (ObjectUtils.isEmpty(sysOrganizationList)) {
      return sysOrganizationTreeVoList;
    }

    Map<String,List<SysOrganization>> treeSysOrganizationMap = new HashMap<>(sysOrganizationList.size());

    //根据name过滤及节点
    if(!ObjectUtils.isEmpty(sysOrganization.getName())){
      getAllSysOrganizationByName(sysOrganizationDTO.getName(),sysOrganizationList, treeSysOrganizationMap);
    }else {
      treeSysOrganizationMap = sysOrganizationList.stream().collect(Collectors.groupingBy(SysOrganization::getLevel));
    }

    List<SysOrganizationTreeVO> sysOrganizationTreeVOList = new ArrayList<>();
    for (Map.Entry<String,List<SysOrganization>> map : treeSysOrganizationMap.entrySet()){
      map.getValue().forEach(org->{
        SysOrganizationTreeVO sysOrganizationTreeVO = new SysOrganizationTreeVO();
        BeanUtils.copyProperties(org,sysOrganizationTreeVO);
        sysOrganizationTreeVOList.add(sysOrganizationTreeVO);
      });
    }

    //根据过滤后的节点生成树
    return List2Tree.bulidTree(sysOrganizationTreeVOList,CommonConstant.ZERO_STR);

  }

  /**
   * 根据节点名称过滤节点
   * @param name  节点名称
   * @param sysOrganizations  全部节点集合
   * @param treeSysOrganizationMap  存储map
   * @Author Leeyoung
   * @Date 2021/1/30
   **/
  private void getAllSysOrganizationByName(String name,
                                           List<SysOrganization> sysOrganizations,
                                           Map<String, List<SysOrganization>> treeSysOrganizationMap) {

    sysOrganizations.forEach(sysOrganization -> {
      if(sysOrganization.getName().contains(name)){
        addFilterList(sysOrganization,treeSysOrganizationMap);
        getParentMap(sysOrganization,sysOrganizations,treeSysOrganizationMap);
      }
    });
  }

  /**
   * 过滤已经添加的节点
   * @param sysOrganization 单个节点
   * @param treeSysOrganizationMap 存储map
   * @Author Leeyoung
   * @Date 2021/1/30
   **/
  private void addFilterList(SysOrganization sysOrganization, Map<String, List<SysOrganization>> treeSysOrganizationMap){

    String level =  sysOrganization.getLevel();
    if(treeSysOrganizationMap.get(level) == null){
      List<SysOrganization> sysOrganizationList = new ArrayList<>();
      sysOrganizationList.add(sysOrganization);
      treeSysOrganizationMap.put(sysOrganization.getLevel(),sysOrganizationList);
    }else {
      boolean isExist = false;
      List<SysOrganization> sysOrganizationList = treeSysOrganizationMap.get(level);
      for (int i=0;i<sysOrganizationList.size();i++){
        if (sysOrganizationList.get(i).getId().equals(sysOrganization.getId())){
          isExist = true;
        }
      }

      if(!isExist){
        sysOrganizationList.add(sysOrganization);
      }

    }
  }

  /**
   * 获取父节点
   * @param sysChildOrganization	子节点
   * @param sysOrganizations	  所有节点
   * @param treeSysOrganizationMap 存储map
   * @Author LeeYoung
   * @Date 2021/1/30
   **/
  private void getParentMap(SysOrganization sysChildOrganization,
                            List<SysOrganization> sysOrganizations,
                            Map<String, List<SysOrganization>> treeSysOrganizationMap){

    if(!sysChildOrganization.getLevel().equals(CommonConstant.LEVEL_TOP)){
      sysOrganizations.forEach(sysOrganization -> {
        if(sysOrganization.getId().equals(sysChildOrganization.getPid())){
          addFilterList(sysOrganization,treeSysOrganizationMap);
          getParentMap(sysOrganization,sysOrganizations,treeSysOrganizationMap);
        };
      });
    }
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
