package com.tf.smart.community.wechat.controller.sysmenu;

import com.tf.smart.community.wechat.entity.vo.sysmenu.SysMenuVO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuDTO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuUpdateDTO;
import com.tf.smart.community.wechat.entity.dto.sysmenu.SysMenuQueryDTO;
import com.tf.smart.community.wechat.service.sysmenu.ISysMenuService;
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
 * 权限定义表 前端控制器
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-29
 */
@RestController
@RequestMapping("/api/sys-menu")
@Api(tags = "权限定义表操作-LeeYoung", value = "权限定义表操作-LeeYoung")
@Validated
public class SysMenuController {

    /**
     * 权限定义表相关Service
     */
    @Autowired
    private ISysMenuService iSysMenuService;

    /**
     * 权限定义表列表
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "权限定义表列表", response = SysMenuVO.class)
    @PostMapping(value = "/listNoPage")
    public CommonResponse listNoPage(@Valid @RequestBody SysMenuQueryDTO param) {
        List<SysMenuVO> pageList = iSysMenuService.listNoPage(param);
        return CommonResponse.success(pageList);
    }

    /**
     * 权限定义表列表(分页)
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "权限定义表列表(分页)", response = SysMenuVO.class)
    @PostMapping(value = "/list")
    public CommonResponse list(@Valid @RequestBody SysMenuQueryDTO param) {
        IPage<SysMenuVO> pageList = iSysMenuService.list(param);
        return CommonResponse.page(pageList);
    }

    /**
     * 权限定义表实体详情
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "权限定义表实体详情", response = SysMenuVO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "id", name = "id", value = "权限定义表 主键", dataType = "Long", required = true)
    })
    @GetMapping("/detail/{id}")
    public CommonResponse detail(@Valid @NotBlank(message = "主键不能为空")  @PathVariable("id") String id) {
        SysMenuVO data = iSysMenuService.get(id);
        return CommonResponse.success(data);
    }

    /**
     * 新增权限定义表实体
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "新增权限定义表实体")
    @PostMapping("/add")
    public CommonResponse add(@Validated(Save.class) @RequestBody SysMenuDTO param) {
        boolean isSuccess = iSysMenuService.save(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 修改权限定义表实体
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "编辑权限定义表实体")
    @PutMapping("/update")
    public CommonResponse update(@Validated(Update.class) @RequestBody SysMenuUpdateDTO param) {
        boolean isSuccess = iSysMenuService.update(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 删除权限定义表实体
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "删除权限定义表实体")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "权限定义表 主键", dataType = "Long", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@Valid @NotBlank(message = "主键不能为空") @PathVariable("id") String id) {
        boolean isSuccess = iSysMenuService.deleteByID(id);
        return CommonResponse.success(isSuccess);
    }


    /**
     * 导出权限定义表列表
     * @Author LeeYoung
     * @Date 2021-01-29
     **/
    @ApiOperation(value = "导出权限定义表列表")
    @PostMapping(value = "/excel")
    public void excel(@Valid @RequestBody SysMenuQueryDTO param, HttpServletResponse response) {
        List<SysMenuVO> SysMenuVOs = iSysMenuService.listNoPage(param);
        EasyPoiExcelUtil.exportExcel(SysMenuVOs, "权限定义表列表", "权限定义表列表", SysMenuVO.class, "权限定义表列表.xls", response);
    }
}
