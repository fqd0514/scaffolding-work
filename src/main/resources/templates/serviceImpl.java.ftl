package ${package.Service}.${entity?lower_case}.impl;

import ${package.Entity}.po.${entity?lower_case}.${entity};
import ${package.Entity}.dto.${entity?lower_case}.${entity}DTO;
import ${package.Entity}.query.${entity?lower_case}.${entity}QueryDTO;
import ${package.Mapper}.${entity?lower_case}.${table.mapperName};
import ${package.Service}.${entity?lower_case}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${package.Entity}.vo.${entity?lower_case}.${entity}Vo;
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
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

  /**
   * ${table.comment}列表
   * @Author ${author}
   * @Date ${date}
   **/
  @Override
  public List<${entity}Vo> listNoPage(${entity}QueryDTO param) {
    QueryWrapper<${entity}> queryWrapper = Wrappers.query();
    List<${entity}> ${entity?uncap_first}List = list(queryWrapper);
    List<${entity}Vo> ${entity?uncap_first}Vos = new ArrayList<>();
    ${entity?uncap_first}List.forEach(${entity?uncap_first} -> {
      ${entity}Vo ${entity?uncap_first}Vo = new ${entity}Vo();
      BeanUtils.copyProperties(${entity?uncap_first}, ${entity?uncap_first}Vo);
      ${entity?uncap_first}Vos.add(${entity?uncap_first}Vo);
    });
    return ${entity?uncap_first}Vos;
  }

  /**
   * ${table.comment}列表(分页)
   * @Author ${author}
   * @Date ${date}
   **/
  @Override
  public IPage<${entity}Vo> list(${entity}QueryDTO param) {
    IPage<${entity}> page = new Page<>(param.getPage(),param.getSize());
    QueryWrapper<${entity}> queryWrapper = Wrappers.query();
    IPage<${entity}> pageList = page(page);
    List<${entity}Vo> ${entity?uncap_first}Vos = new ArrayList<>();
    pageList.getRecords().forEach(${entity?uncap_first} -> {
    ${entity}Vo ${entity?uncap_first}Vo = new ${entity}Vo();
        BeanUtils.copyProperties(${entity?uncap_first}, ${entity?uncap_first}Vo);
        ${entity?uncap_first}Vos.add(${entity?uncap_first}Vo);
    });

    IPage<${entity}Vo> responsePage = new Page<>();
    responsePage.setRecords(${entity?uncap_first}Vos);
    responsePage.setCurrent(pageList.getCurrent());
    responsePage.setTotal(pageList.getTotal());
    responsePage.setPages(pageList.getPages());
    return responsePage;
  }

  /**
   * ${table.comment}实体详情
   * @Author ${author}
   * @Date ${date}
   **/
  @Override
  public ${entity}Vo get(String id) {
    ${entity} ${entity?uncap_first} = getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(${entity?uncap_first});
    ${entity}Vo ${entity?uncap_first}Vo = new ${entity}Vo();
    BeanUtils.copyProperties(${entity?uncap_first}, ${entity?uncap_first}Vo);
    return ${entity?uncap_first}Vo ;
  }

  /**
  * 新增${table.comment}实体
  * @Author ${author}
  * @Date ${date}
  **/
  @Override
  public boolean save(${entity}DTO param) {
    ${entity} ${entity?uncap_first} = new ${entity}();
    BeanUtils.copyProperties(param, ${entity?uncap_first});
    return save(${entity?uncap_first});
  }


  /**
   * 修改${table.comment}实体
   * @Author ${author}
   * @Date ${date}
   **/
  @Override
  public boolean update(${entity}DTO param) {
    ${entity} ${entity?uncap_first} = new ${entity}();
    BeanUtils.copyProperties(param, ${entity?uncap_first});
    return updateById(${entity?uncap_first});
  }


  /**
   * 删除${table.comment}实体
   * @Author ${author}
   * @Date ${date}
   **/
  @Override
  public boolean deleteByID(String id) {
    ${entity} ${entity?uncap_first} = this.getById(id);
    CommonResponseEnum.RESULT_IS_NULL.assertNotNull(${entity?uncap_first});
    return removeById(id);
  }
}
</#if>
