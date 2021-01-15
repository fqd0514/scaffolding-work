package ${package.Controller}.${entity?lower_case};

import ${package.Entity}.vo.${entity?lower_case}.${entity}Vo;
import ${package.Entity}.dto.${entity?lower_case}.${entity}Dto;
import ${package.Entity}.query.${entity?lower_case}.${entity}Query;
import ${package.Service}.${entity?lower_case}.${table.serviceName};
<#if cfg.isRecordOperateLog?string("true","flase") ='true'>
import com.tf.smart.community.wechat.common.annotation.OperateLog;
</#if>
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
<#if cfg.isGenerateExcel?string("true","flase") ='true'>
import com.tf.smart.community.wechat.common.utils.EasyPoiExcelUtil;
import javax.servlet.http.HttpServletResponse;
</#if>

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Api(tags = "${table.comment}操作-${author}", value = "${table.comment}操作-${author}")
@Validated
public class ${table.controllerName} {
</#if>

    /**
     * ${table.comment!}相关Service
     */
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * ${table.comment}列表
     * @Author ${author}
     * @Date ${date}
     **/
    @ApiOperation(value = "${table.comment}列表", response = ${entity}Vo.class)
    @PostMapping(value = "/listNoPage")
    public CommonResponse listNoPage(@Valid @RequestBody ${entity}Query param) {
        List<${entity}Vo> pageList = ${table.serviceName?uncap_first}.listNoPage(param);
        return CommonResponse.success(pageList);
    }

    /**
     * ${table.comment}列表(分页)
     * @Author ${author}
     * @Date ${date}
     **/
    @ApiOperation(value = "${table.comment}列表(分页)", response = ${entity}Vo.class)
    @PostMapping(value = "/list")
    public CommonResponse list(@Valid @RequestBody ${entity}Query param) {
        IPage<${entity}Vo> pageList = ${table.serviceName?uncap_first}.list(param);
        return CommonResponse.page(pageList);
    }

    /**
     * ${table.comment}实体详情
     * @Author ${author}
     * @Date ${date}
     **/
    @ApiOperation(value = "${table.comment}实体详情", response = ${entity}Vo.class)
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "id", name = "id", value = "${table.comment} 主键", dataType = "Long", required = true)
    })
    @GetMapping("/detail/{id}")
    public CommonResponse detail(@Valid @NotBlank(message = "主键不能为空")  @PathVariable("id") String id) {
        ${entity}Vo data = ${table.serviceName?uncap_first}.get(id);
        return CommonResponse.success(data);
    }

    /**
     * 新增${table.comment}实体
     * @Author ${author}
     * @Date ${date}
     **/
    @ApiOperation(value = "新增${table.comment}实体")
    @PostMapping("/add")
    public CommonResponse add(@Validated(Save.class) @RequestBody ${entity}Dto param) {
        boolean isSuccess = ${table.serviceName?uncap_first}.save(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 修改${table.comment}实体
     * @Author ${author}
     * @Date ${date}
     **/
    @ApiOperation(value = "编辑${table.comment}实体")
    @PutMapping("/update")
    public CommonResponse update(@Validated(Update.class) @RequestBody ${entity}Dto param) {
        boolean isSuccess = ${table.serviceName?uncap_first}.update(param);
        return CommonResponse.success(isSuccess);
    }

    /**
     * 删除${table.comment}实体
     * @Author ${author}
     * @Date ${date}
     **/
    @ApiOperation(value = "删除${table.comment}实体")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "${table.comment} 主键", dataType = "Long", required = true)
    })
    @DeleteMapping("/delete/{id}")
    public CommonResponse delete(@Valid @NotBlank(message = "主键不能为空") @PathVariable("id") String id) {
        boolean isSuccess = ${table.serviceName?uncap_first}.deleteByID(id);
        return CommonResponse.success(isSuccess);
    }


    /**
     * 导出${table.comment}列表
     * @Author ${author}
     * @Date ${date}
     **/
<#if cfg.isGenerateExcel?string("true","flase") ='true'>
    @ApiOperation(value = "导出${table.comment}列表")
    @PostMapping(value = "/excel")
    public void excel(@Valid @RequestBody ${entity}Query param, HttpServletResponse response) {
        List<${entity}Vo> ${entity}Vos = ${table.serviceName?uncap_first}.listNoPage(param);
        EasyPoiExcelUtil.exportExcel(${entity}Vos, "${table.comment}列表", "${table.comment}列表", ${entity}Vo.class, "${table.comment}列表.xls", response);
    }
</#if>
}
</#if>
