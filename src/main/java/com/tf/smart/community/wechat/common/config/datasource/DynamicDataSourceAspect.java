package com.tf.smart.community.wechat.common.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * 多数据源切换 Aspect
 *
 * @author HelloWood
 * @date 2017-08-15 11:37
 * @email hellowoodes@gmail.com
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    /**
     * Dao aspect.
     */
    @Pointcut("execution( * com.tf.smart.community.wechat.dao..*.*(..))")
    public void daoAspect() {
    }

    /**
     * 切换数据源
     *
     * @param point the point
     */
    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
//        Boolean isQueryMethod = isQueryMethod(point.getSignature().getName());
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0] != null) {

            String datasource = "";
            if (getValueByKey(args[0], "datasource") != "") {
                datasource = getValueByKey(args[0], "datasource").toString();
            }
            if (datasource != "" && datasource.length() > 0) {
//            DynamicDataSourceContextHolder.useSlaveDataSource();
                DynamicDataSourceContextHolder.setDataSourceRouterKey(datasource);
                logger.debug("Switch DataSource to [{}] in Method [{}]",
                        DynamicDataSourceContextHolder.getDataSourceRouterKey(), point.getSignature());
            } else {
                DynamicDataSourceContextHolder.setDataSourceRouterKey("master");
            }
        } else {
            DynamicDataSourceContextHolder.setDataSourceRouterKey("master");
        }

    }

    /**
     * 释放数据源
     *
     * @param point the point
     */
    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
        logger.debug("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceRouterKey(), point.getSignature());
    }

    /**
     * 单个对象的某个键的值
     *
     * @param key
     * @return Object 键在对象中所对应得值 没有查到时返回空字符串
     */
    public static Object getValueByKey(Object obj, String key) {
        // 得到类对象
        Class userCla = obj.getClass();
        /* 得到类中的所有属性集合 */
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); // 设置些属性是可以访问的
            try {
                if (f.getName().endsWith(key)) {
                    System.out.println("单个对象的某个键的值==反射==" + f.get(obj));
                    return f.get(obj);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // 没有查到时返回空字符串
        return "";
    }
}
