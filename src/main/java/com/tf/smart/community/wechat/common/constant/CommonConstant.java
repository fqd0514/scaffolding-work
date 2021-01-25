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
    public static final String REDIS_SYS_CITY = "sys:city";
    public static final String REDIS_SYS_CITY_INIT = "sys:city:init";
    public static final String REDIS_SYS_DISTRICT = "sys:district";
    public static final String REDIS_SYS_DISTRICT_INIT = "sys:district:init";
    public static final String REDIS_SYS_STREET = "sys:street";
    public static final String REDIS_SYS_STREET_INIT = "sys:street:init";
    public static final String REDIS_SYS_COMMUNITY = "sys:community";
    public static final String REDIS_SYS_COMMUNITY_INIT = "sys:community:init";
    public static final String REDIS_SYS_VILLAGE = "sys:village";
    public static final String REDIS_SYS_VILLAGE_INIT = "sys:village:init";

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


    public static final String CODE_NAME_LABEL_TYPE = "label_type";
    public static final String CODE_NAME_ACTIVITY_STATUS = "activity_status";
    public static final String CODE_NAME_ACTIVITY_TYPE = "activity_type";
    public static final String CODE_NAME_BULLETIN_STATUS = "bulletin_status";
    public static final String CODE_NAME_GENDER = "gender";
    public static final String CODE_NAME_CERTIFICATION_TYPE = "certification_type";
    public static final String CODE_NAME_VOTING_MODE = "voting_mode";
    public static final String CODE_NAME_VOTING_STATUS = "voting_status";


    public static final String REDIS_LOCK_ACTIVITY = "REDIS_LOCK_ACTIVITY";


    public static final String WECHAT_SMS = "您的验证码%s，该验证码5分钟内有效，请勿泄漏于他人！";

}
