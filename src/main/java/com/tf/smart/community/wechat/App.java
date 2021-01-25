package com.tf.smart.community.wechat;

import com.tf.smart.community.wechat.common.config.datasource.DataSourceConfigurer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用
 *
 * @author LeeYoung
 */
@SpringBootApplication
@ServletComponentScan
@Import(DataSourceConfigurer.class)
@MapperScan({"com.tf.smart.community.wechat.dao.*"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
