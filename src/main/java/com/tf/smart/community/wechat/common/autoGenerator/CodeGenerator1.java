package com.tf.smart.community.wechat.common.autoGenerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 代码生成器
 * @Author Leeyoung
 * @Date 2021/1/22
 **/
public class CodeGenerator1 {

    //自定义模板MAP
    public static Map<String, String> templateMap;

    //自定义属性
    public static Map<String, Object> map;


    static {
        templateMap = ImmutableMap.<String, String>builder()
                .put("templates/controller.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/controller/{1}/{2}"
                        + "Controller" + StringPool.DOT_JAVA)
                .put("templates/entity.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/entity/po/{1}/{2}"
                        + StringPool.DOT_JAVA)
                .put("templates/entityDto.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/entity/dto/{1}/{2}"
                        + "DTO" + StringPool.DOT_JAVA)
                .put("templates/entityVo.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/entity/vo/{1}/{2}"
                        + "VO" + StringPool.DOT_JAVA)
                .put("templates/entityQuery.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/entity/dto/{1}/{2}"
                        + "QueryDTO" + StringPool.DOT_JAVA)
                .put("templates/entityUpdateDto.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/entity/dto/{1}/{2}"
                        + "UpdateDTO" + StringPool.DOT_JAVA)
                .put("/templates/mapper.xml.ftl" , "{0}/src/main/resources/mapper/{1}/{2}"
                        + "Mapper" + StringPool.DOT_XML)
                .put("templates/mapper.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/dao/{1}/{2}"
                        + "Mapper" + StringPool.DOT_JAVA)
                .put("templates/service.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/service/{1}/I{2}"
                        + "Service" + StringPool.DOT_JAVA)
                .put("templates/serviceImpl.java.ftl" , "{0}/src/main/java/com/tf/smart/community/wechat/service/{1}/impl/{2}"
                        + "ServiceImpl" + StringPool.DOT_JAVA)
                .build();

        //isGenerateExcel 是否生成导出excel true,false
        //isRecordOperateLog 是否生成导出操作日志 true,false
        map = ImmutableMap.<String, Object>builder()
                .put("isGenerateExcel" , true)
                .put("isRecordOperateLog",true)
                .build();


    }

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = getInjectionConfig();
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = getTemplateConfig();
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    /**
     * 策略配置
     * @return
     */
    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        //查询对象集成分页baseModel
        strategy.setSuperEntityClass("com.tf.smart.community.wechat.entity.dto.base.PageDTO");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setEntitySerialVersionUID(false);
        //逻辑删除
        strategy.setLogicDeleteFieldName("is_del");
        //自动填充
        strategy.setTableFillList(Lists.newArrayList(new TableFill("CREATE_TIME" , FieldFill.INSERT),
                new TableFill("CREATE_USER_ID" , FieldFill.INSERT), new TableFill("UPDATE_TIME" , FieldFill.UPDATE),
                new TableFill("UPDATE_USER_ID" , FieldFill.UPDATE), new TableFill("STATUS" , FieldFill.INSERT)));

        // 写于父类中的公共字段
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);

        return strategy;
    }

    /**
     * 配置模板
     * @return
     */
    private static TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setEntity(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        return templateConfig;
    }

    /**
     * 自定义配置
     * @return
     */
    private static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                this.setMap(map);
            }
        };

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        templateMap.forEach((k, v) -> {
            focList.add(new FileOutConfig(k) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    return MessageFormat.format(v, System.getProperty("user.dir"), tableInfo.getEntityName().toLowerCase(), tableInfo.getEntityName());
                }
            });
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 包配置
     * @return
     */
    private static PackageConfig getPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tf.smart.community.wechat");
        pc.setMapper("dao");
        return pc;
    }

    /**
     * 全局配置
     * @return
     */
    private static GlobalConfig getGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor("LeeYoung");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 是否打开输出目录 默认为true
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        return gc;
    }

    /**
     * 数据源配置
     * @return
     */
    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.133.50:3306/scaffold?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        return dsc;
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
