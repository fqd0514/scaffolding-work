package com.tf.smart.community.wechat.service.cmsarea;

import com.tf.smart.community.wechat.entity.po.cmsarea.CmsArea;
import com.tf.smart.community.wechat.entity.vo.cmsarea.CmsAreaVo;
import com.tf.smart.community.wechat.entity.dto.cmsarea.CmsAreaDto;
import com.tf.smart.community.wechat.entity.query.cmsarea.CmsAreaQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * <p>
 * CMS 区域表 服务类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-15
 */
public interface ICmsAreaService extends IService<CmsArea> {


    /**
    * 获取实体list
    *
    * @return
    */
    List<CmsAreaVo> listNoPage(CmsAreaQuery param);

    /**
    * 获取实体list分页
    *
    * @param param
    * @return
    */
    IPage<CmsAreaVo> list(CmsAreaQuery param);

    /**
    * 获取实体详情
    *
    * @param id
    * @return
    */
    CmsAreaVo get(String id);

    /**
    * 保存实体
    *
    * @param param
    * @return
    */
    boolean save(CmsAreaDto param);

    /**
    * 更新实体
    *
    * @param param
    * @return
    */
    boolean update(CmsAreaDto param);

    /**
    * 根据ID删除
    *
    * @param id
    * @return
    */
    boolean deleteByID(String id);
}
