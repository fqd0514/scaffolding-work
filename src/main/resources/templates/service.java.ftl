package ${package.Service}.${entity?lower_case};

import ${package.Entity}.po.${entity?lower_case}.${entity};
import ${package.Entity}.vo.${entity?lower_case}.${entity}VO;
import ${package.Entity}.dto.${entity?lower_case}.${entity}UpdateDTO;
import ${package.Entity}.dto.${entity?lower_case}.${entity}DTO;
import ${package.Entity}.dto.${entity?lower_case}.${entity}QueryDTO;
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {


    /**
    * 获取实体list
    *
    * @return
    */
    List<${entity}VO> listNoPage(${entity}QueryDTO param);

    /**
    * 获取实体list分页
    *
    * @param param
    * @return
    */
    IPage<${entity}VO> list(${entity}QueryDTO param);

    /**
    * 获取实体详情
    *
    * @param id
    * @return
    */
    ${entity}VO get(String id);

    /**
    * 保存实体
    *
    * @param param
    * @return
    */
    boolean save(${entity}DTO param);

    /**
    * 更新实体
    *
    * @param param
    * @return
    */
    boolean update(${entity}UpdateDTO param);

    /**
    * 根据ID删除
    *
    * @param id
    * @return
    */
    boolean deleteByID(String id);
}
</#if>
