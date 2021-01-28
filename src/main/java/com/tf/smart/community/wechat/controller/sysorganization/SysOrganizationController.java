package com.tf.smart.community.wechat.controller.sysorganization;

import com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationTreeVO;
import com.tf.smart.community.wechat.entity.vo.sysorganization.SysOrganizationVO;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationDTO;
import com.tf.smart.community.wechat.entity.dto.sysorganization.SysOrganizationQueryDTO;
import com.tf.smart.community.wechat.service.sysorganization.ISysOrganizationService;
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
 * 机构表 前端控制器
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/api/sysOrganization")
@Api(tags = "机构表操作-LeeYoung", value = "机构表操作-LeeYoung")
@Validated
public class SysOrganizationController {

    /**
     * 机构表相关Service
     */
    @Autowired
    private ISysOrganizationService iSysOrganizationService;

    /**
     * 机构表列表
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "机构表列表", response = SysOrganizationVO.class)
    @PostMapping(value = "/listNoPage")
    public CommonResponse listNoPage(@Valid @RequestBody SysOrganizationQueryDTO param) {
        List<SysOrganizationVO> pageList = iSysOrganizationService.listNoPage(param);
        return CommonResponse.success(pageList);
    }

    /**
     * 机构表列表(分页)
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "机构表列表(分页)", response = SysOrganizationVO.class)
    @PostMapping(value = "/list")
    public CommonResponse list(@Valid @RequestBody SysOrganizationQueryDTO param) {
        IPage<SysOrganizationVO> pageList = iSysOrganizationService.list(param);
        return CommonResponse.page(pageList);
    }

    /**
     * 机构表实体详情
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "机构表实体详情", response = SysOrganizationVO.class)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "id", name = "id", value = "机构表 主键", dataType = "Long", required = true)
    })
    @GetMapping("/detail/{id}")
    public CommonResponse detail(@Valid @NotBlank(message = "主键不能为空")  @PathVariable("id") String id) {
        SysOrganizationVO data = iSysOrganizationService.get(id);
        return CommonResponse.success(data);
    }

    /**
     * 新增机构表实体
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "新增机构表实体")
    @PostMapping("/add")
    public CommonResponse add(@Validated(Save.class) @RequestBody SysOrganizationDTO param) {
        boolean isSuccess = iSysOrganizationService.save(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 修改机构表实体
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "编辑机构表实体")
    @PutMapping("/update")
    public CommonResponse update(@Validated(Update.class) @RequestBody SysOrganizationDTO param) {
        boolean isSuccess = iSysOrganizationService.update(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 删除机构表实体
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "删除机构表实体")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "机构表 主键", dataType = "Long", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@Valid @NotBlank(message = "主键不能为空") @PathVariable("id") String id) {
        boolean isSuccess = iSysOrganizationService.deleteByID(id);
        return CommonResponse.success(isSuccess);
    }


    /**
     * 导出机构表列表
     * @Author LeeYoung
     * @Date 2021-01-25
     **/
    @ApiOperation(value = "导出机构表列表")
    @PostMapping(value = "/excel")
    public void excel(@Valid @RequestBody SysOrganizationQueryDTO param, HttpServletResponse response) {
        List<SysOrganizationVO> SysOrganizationVOs = iSysOrganizationService.listNoPage(param);
        EasyPoiExcelUtil.exportExcel(SysOrganizationVOs, "机构表列表", "机构表列表", SysOrganizationVO.class, "机构表列表.xls", response);
    }

    /**
     * 机构树
     * @param sysOrganizationDTO 机构查询条件
     * @return com.tf.smart.community.wechat.common.entity.response.CommonResponse<java.util.List<SysOrganizationTreeVo>>
     * @Author Leeyoung
     * @Date 2021/1/27
     **/
    @ApiOperation(value = "机构树", notes = "机构树")
    @PostMapping("/tree")
    public CommonResponse<List<SysOrganizationTreeVO>> tree(@RequestBody SysOrganizationDTO sysOrganizationDTO) {
        List<SysOrganizationTreeVO> tree = iSysOrganizationService.tree(sysOrganizationDTO);
        return CommonResponse.success(tree);
    }
}
