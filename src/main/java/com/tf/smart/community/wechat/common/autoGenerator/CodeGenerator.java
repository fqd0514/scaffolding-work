//package com.tf.smart.community.wechat.common.autoGenerator;
//
//
//import com.baomidou.mybatisplus.core.config.GlobalConfig;
//
///**
// * @Description: 代码生成器
// */
//public class CodeGenerator {
//
//    public static final String DB_URL = "jdbc:mysql://192.168.133.50:3306/smart_community?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
//    public static final String USER_NAME = "root";
//    public static final String PASSWORD = "123456";
//    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    public static final String AUTHOR = "zk";
//    //生成的文件输出到哪个目录
//    public static final String OUTPUT_FILE = "E:\\work\\scaffolding-work\\src\\main\\java";
//
//    //包名，会按照com/example/demo这种形式生成类
//    public static final String PACKAGE = "com.tf.smart.community.wechat";
//    //TODO 更多配置请参考http://mp.baomidou.com/#/generate-code
//
//    public static void main(String[] args) {
//        generateByTables(false,"resident_owner");
//    }
//    public static void generateByTables(boolean serviceNameStartWithI, String... tableNames) {
//        GlobalConfig config = new GlobalConfig();
//        DataSourceConfig dataSourceConfig = new DataSourceConfig();
//        dataSourceConfig
//                .setUrl(DB_URL)
//                .setUsername(USER_NAME)
//                .setPassword(PASSWORD)
//                .setDriverName(DRIVER);
//        StrategyConfig strategyConfig = new StrategyConfig();
//        strategyConfig
//                .setCapitalMode(true)
//                .setEntityLombokModel(false)
//                .setNaming(NamingStrategy.underline_to_camel)
//                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
//        config.setActiveRecord(false)
//                .setAuthor(AUTHOR)
//                .setOutputDir(OUTPUT_FILE)
//                .setFileOverride(true);
//        if (!serviceNameStartWithI) {
//            config.setServiceName("%sService");
//        }
//        new AutoGenerator().setGlobalConfig(config)
//                .setDataSource(dataSourceConfig)
//                .setStrategy(strategyConfig)
//                .setPackageInfo(
//                        new PackageConfig()
//                                .setParent(PACKAGE)
//                                .setController("controller")
//                                .setEntity("model")
//                ).execute();
//    }
//}
