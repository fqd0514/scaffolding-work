package com.tf.smart.community.wechat.service.sysmenu;

import com.tf.smart.community.wechat.entity.po.sysmenu.SysMenu;
import com.tf.smart.community.wechat.entity.vo.sysmenu.SysMenuVO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuUpdateDTO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuDTO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * <p>
 * 权限定义表 服务类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-29
 */
public interface ISysMenuService extends IService<SysMenu> {


    /**
    * 获取实体list
    *
    * @return
    */
    List<SysMenuVO> listNoPage(SysMenuQueryDTO param);

    /**
    * 获取实体list分页
    *
    * @param param
    * @return
    */
    IPage<SysMenuVO> list(SysMenuQueryDTO param);

    /**
    * 获取实体详情
    *
    * @param id
    * @return
    */
    SysMenuVO get(String id);

    /**
    * 保存实体
    *
    * @param param
    * @return
    */
    boolean save(SysMenuDTO param);

    /**
    * 更新实体
    *
    * @param param
    * @return
    */
    boolean update(SysMenuUpdateDTO param);

    /**
    * 根据ID删除
    *
    * @param id
    * @return
    */
    boolean deleteByID(String id);
}
