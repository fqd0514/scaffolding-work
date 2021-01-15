package com.tf.smart.community.wechat.controller.cmsarea;

import com.tf.smart.community.wechat.entity.vo.cmsarea.CmsAreaVo;
import com.tf.smart.community.wechat.entity.dto.cmsarea.CmsAreaDto;
import com.tf.smart.community.wechat.entity.query.cmsarea.CmsAreaQuery;
import com.tf.smart.community.wechat.service.cmsarea.ICmsAreaService;
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
 * CMS 区域表 前端控制器
 * </p>
 *
 * @author LeeYoung
 * @since 2021-01-15
 */
@RestController
@RequestMapping("/cmsArea")
@Api(tags = "CMS 区域表操作-LeeYoung", value = "CMS 区域表操作-LeeYoung")
@Validated
public class CmsAreaController {

    /**
     * CMS 区域表相关Service
     */
    @Autowired
    private ICmsAreaService iCmsAreaService;

    /**
     * CMS 区域表列表
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "CMS 区域表列表", response = CmsAreaVo.class)
    @PostMapping(value = "/listNoPage")
    public CommonResponse listNoPage(@Valid @RequestBody CmsAreaQuery param) {
        List<CmsAreaVo> pageList = iCmsAreaService.listNoPage(param);
        return CommonResponse.success(pageList);
    }

    /**
     * CMS 区域表列表(分页)
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "CMS 区域表列表(分页)", response = CmsAreaVo.class)
    @PostMapping(value = "/list")
    public CommonResponse list(@Valid @RequestBody CmsAreaQuery param) {
        IPage<CmsAreaVo> pageList = iCmsAreaService.list(param);
        return CommonResponse.page(pageList);
    }

    /**
     * CMS 区域表实体详情
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "CMS 区域表实体详情", response = CmsAreaVo.class)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "id", name = "id", value = "CMS 区域表 主键", dataType = "Long", required = true)
    })
    @GetMapping("/detail/{id}")
    public CommonResponse detail(@Valid @NotBlank(message = "主键不能为空")  @PathVariable("id") String id) {
        CmsAreaVo data = iCmsAreaService.get(id);
        return CommonResponse.success(data);
    }

    /**
     * 新增CMS 区域表实体
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "新增CMS 区域表实体")
    @PostMapping("/add")
    public CommonResponse add(@Validated(Save.class) @RequestBody CmsAreaDto param) {
        boolean isSuccess = iCmsAreaService.save(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 修改CMS 区域表实体
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "编辑CMS 区域表实体")
    @PutMapping("/update")
    public CommonResponse update(@Validated(Update.class) @RequestBody CmsAreaDto param) {
        boolean isSuccess = iCmsAreaService.update(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 删除CMS 区域表实体
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "删除CMS 区域表实体")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "CMS 区域表 主键", dataType = "Long", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@Valid @NotBlank(message = "主键不能为空") @PathVariable("id") String id) {
        boolean isSuccess = iCmsAreaService.deleteByID(id);
        return CommonResponse.success(isSuccess);
    }


    /**
     * 导出CMS 区域表列表
     * @Author LeeYoung
     * @Date 2021-01-15
     **/
    @ApiOperation(value = "导出CMS 区域表列表")
    @PostMapping(value = "/excel")
    public void excel(@Valid @RequestBody CmsAreaQuery param, HttpServletResponse response) {
        List<CmsAreaVo> CmsAreaVos = iCmsAreaService.listNoPage(param);
        EasyPoiExcelUtil.exportExcel(CmsAreaVos, "CMS 区域表列表", "CMS 区域表列表", CmsAreaVo.class, "CMS 区域表列表.xls", response);
    }
}
