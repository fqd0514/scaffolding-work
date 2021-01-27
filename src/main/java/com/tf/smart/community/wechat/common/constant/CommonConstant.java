package com.tf.smart.community.wechat.common.constant;

/**
 * 通用常量
 *
 * @author 翟晶
 */
public class CommonConstant {
    public static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");

    // REDIS 缓存系统编码
    public static final String REDIS_SEPARATOR = ":";
    public static final String REDIS_SYSCODE = "sys_code";
    public static final String REDIS_SYSCODE_INIT = "sys_code:init";

    //redis登录key
    public static final String REDIS_TOKEN = "token:";

    //Redis 缓存序列号(format: sys_sequence:{sequenceGroup}:{sequenceType})
    public static final String REDIS_SEQ = "sys_sequence:%s:%s";
    public static final String REDIS_RESIDENT_LABEL_INIT = "resident_label:init";

    //逗号
    public static final String COMMA = ",";
    //水平线
    public static final String HORIZONTAL_LINE = "-";
    //星
    public static final String STAR = "*";
    //分号
    public static final String SEMICOLON = ";";
    //百分号
    public static final String PERCENT = "%";
    //点
    public static final String DOT = ".";

    //0的string状态
    public static final String ZERO_STR = "0";

    //0的integer状态
    public static final Integer ZERO_INT = 0;


    public static final String REDIS_LOCK_ACTIVITY = "REDIS_LOCK_ACTIVITY";


    public static final String WECHAT_SMS = "您的验证码%s，该验证码5分钟内有效，请勿泄漏于他人！";

}
