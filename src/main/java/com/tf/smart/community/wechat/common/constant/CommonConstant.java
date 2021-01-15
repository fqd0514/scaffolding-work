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

    // 与户主关系 key
    public static final String OWNER_RELATION = "owner_relation";

    // 来访事由 key
    public static final String VISITOR_CAUSE = "visitor_cause";

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
    //问卷调查统计相关key
    public static final String REDIS_QUESTION_COUNT = "question:info";

    //微信的accessToken 的 key
    public static final String WECHAT_ACCESS_TOKEN = "wechat:accToken";

    //微信的ticket 的 key
    public static final String WECHAT_TICKET = "wechat:ticket";

    /**
     * 微信相关失效时间
     */
    public static final String WX_DISABLE_TIME = "6300";

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


    //资产-配件 配件编码序列组别
    public static final String ASSET_FITTING_CODE_SEQUENCE_GROUP = "fitting_code";
    //资产-配件 配件编码格式 F{配件类型}{序列号}
    public static final String ASSET_FITTING_CODE_FORMAT = "F%s%s";
    //任务编码序列组别
    public static final String TASK_CODE_SEQUENCE_GROUP = "task_code";

    //业主特殊人名   失联
    public static final String SPECIAL_ACCOUNT_NAME = "失联";

    //任务编号序列号长度
    public static final Integer TASK_CODE_SEQUENCE_LENGTH = new Integer(3);

    public static final String CODE_NAME_LABEL_TYPE = "label_type";
    public static final String CODE_NAME_ACTIVITY_STATUS = "activity_status";
    public static final String CODE_NAME_ACTIVITY_TYPE = "activity_type";
    public static final String CODE_NAME_BULLETIN_STATUS = "bulletin_status";
    public static final String CODE_NAME_GENDER = "gender";
    public static final String CODE_NAME_CERTIFICATION_TYPE = "certification_type";
    public static final String CODE_NAME_VOTING_MODE = "voting_mode";
    public static final String CODE_NAME_VOTING_STATUS = "voting_status";

    //楼栋汇总
    public static final String BUILD_SUMMARIZE = "buildSummarize";

    //关系汇总
    public static final String RELATION_SUMMARIZE = "relationSummarize";

    public static final String REDIS_LOCK_ACTIVITY = "REDIS_LOCK_ACTIVITY";

    //证件编号序列组别
    public static final String CERTIFICATION_CODE_SEQUENCE_GROUP = "certification_code";
    //证件编号序列号长度
    public static final Integer CERTIFICATION_CODE_SEQUENCE_LENGTH = new Integer(3);

    //微信获取token和openID地址
    public static final String WECHATURLTOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";

    //微信获取用户信息地址
    public static final String WECHATURLINFO = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";

    //微信登录token前缀
    public static final String WECHATTOKENKEY = "wechat:token:";

    //微信短信验证码前缀
    public static final String WECHAT_SMS_CODE = "wechat:sms:code:";

    public static final String WECHAT_SMS = "您的验证码%s，该验证码5分钟内有效，请勿泄漏于他人！";

    //证明文件保存文件夹名称
    public static final String CERTIFICATION_FILE_FOLDER = "certification";

    //World后缀
    public static final String WORD_SUFFIX_DOCX = "docx" ;

    //居委会主任角色部分名称
    public static final String COMMUNITY_DIRECTOR_ROLE_NAME_PART = "主任";
    //居委会书记角色部分名称
    public static final String COMMUNITY_SECRETARY_ROLE_NAME_PART = "书记";

}
