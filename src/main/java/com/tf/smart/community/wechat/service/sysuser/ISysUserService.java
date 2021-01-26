package com.tf.smart.community.wechat.service.sysuser;

import com.tf.smart.community.wechat.entity.po.sysuser.SysUser;
import com.tf.smart.community.wechat.entity.vo.sysuser.SysUserVO;
import com.tf.smart.community.wechat.entity.dto.sysuser.SysUserDTO;
import com.tf.smart.community.wechat.entity.dto.sysuser.SysUserQueryDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-26
 */
public interface ISysUserService extends IService<SysUser> {


    /**
    * 获取实体list
    *
    * @return
    */
    List<SysUserVO> listNoPage(SysUserQueryDTO param);

    /**
    * 获取实体list分页
    *
    * @param param
    * @return
    */
    IPage<SysUserVO> list(SysUserQueryDTO param);

    /**
    * 获取实体详情
    *
    * @param id
    * @return
    */
    SysUserVO get(String id);

    /**
    * 保存实体
    *
    * @param param
    * @return
    */
    boolean save(SysUserDTO param);

    /**
    * 更新实体
    *
    * @param param
    * @return
    */
    boolean update(SysUserDTO param);

    /**
    * 根据ID删除
    *
    * @param id
    * @return
    */
    boolean deleteByID(String id);
}
