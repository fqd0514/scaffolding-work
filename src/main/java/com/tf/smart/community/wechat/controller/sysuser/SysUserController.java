package com.tf.smart.community.wechat.controller.sysuser;

import com.tf.smart.community.wechat.entity.vo.sysuser.SysUserVO;
import com.tf.smart.community.wechat.entity.dto.sysuser.SysUserDTO;
import com.tf.smart.community.wechat.entity.dto.sysuser.SysUserQueryDTO;
import com.tf.smart.community.wechat.service.sysuser.ISysUserService;
import com.tf.smart.community.wechat.common.annotation.OperateLog;
import com.tf.smart.community.wechat.common.validation.Save;
import com.tf.smart.community.wechat.common.validation.Update;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tf.smart.community.wechat.common.constant.ReturnTypeEnum;
import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.entity.response.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import java.util.List;
import com.tf.smart.community.wechat.common.utils.EasyPoiExcelUtil;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/api/sysUser")
@Api(tags = "用户表操作-LeeYoung", value = "用户表操作-LeeYoung")
@Validated
public class SysUserController {

    /**
     * 用户表相关Service
     */
    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 用户表列表
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "用户表列表", response = SysUserVO.class)
    @PostMapping(value = "/listNoPage")
    public CommonResponse listNoPage(@Valid @RequestBody SysUserQueryDTO param) {
        List<SysUserVO> pageList = iSysUserService.listNoPage(param);
        return CommonResponse.success(pageList);
    }

    /**
     * 用户表列表(分页)
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "用户表列表(分页)", response = SysUserVO.class)
    @PostMapping(value = "/list")
    public CommonResponse list(@Valid @RequestBody SysUserQueryDTO param) {
        IPage<SysUserVO> pageList = iSysUserService.list(param);
        return CommonResponse.page(pageList);
    }

    /**
     * 用户表实体详情
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "用户表实体详情", response = SysUserVO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "id", name = "id", value = "用户表 主键", dataType = "Long", required = true)
    })
    @GetMapping("/detail/{id}")
    public CommonResponse detail(@Valid @NotBlank(message = "主键不能为空")  @PathVariable("id") String id) {
        SysUserVO data = iSysUserService.get(id);
        return CommonResponse.success(data);
    }

    /**
     * 新增用户表实体
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "新增用户表实体")
    @PostMapping("/add")
    public CommonResponse add(@Validated(Save.class) @RequestBody SysUserDTO param) {
        boolean isSuccess = iSysUserService.save(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 修改用户表实体
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "编辑用户表实体")
    @PutMapping("/update")
    public CommonResponse update(@Validated(Update.class) @RequestBody SysUserDTO param) {
        boolean isSuccess = iSysUserService.update(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 删除用户表实体
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "删除用户表实体")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "用户表 主键", dataType = "Long", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@Valid @NotBlank(message = "主键不能为空") @PathVariable("id") String id) {
        boolean isSuccess = iSysUserService.deleteByID(id);
        return CommonResponse.success(isSuccess);
    }


    /**
     * 导出用户表列表
     * @Author LeeYoung
     * @Date 2021-01-26
     **/
    @ApiOperation(value = "导出用户表列表")
    @PostMapping(value = "/excel")
    public void excel(@Valid @RequestBody SysUserQueryDTO param, HttpServletResponse response) {
        List<SysUserVO> SysUserVOs = iSysUserService.listNoPage(param);
        EasyPoiExcelUtil.exportExcel(SysUserVOs, "用户表列表", "用户表列表", SysUserVO.class, "用户表列表.xls", response);
    }
}
