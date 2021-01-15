//package com.tf.smart.community.wechat.entity.po.resident;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * 用户_账户表
// * @Author Leeyoung
// * @Date 2020/8/13
// **/
//@Data
//@Table(name = "user_account")
//public class UserAccount {
//
//    /**
//     * 账户_主键
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
//    @Column(name = "account_id")
//    private Long accountId;
//
//    /**
//     * 账户_类型(字典表)：1-居民、2-租户
//     */
//    @Column(name = "account_type")
//    private String accountType;
//
//    /**
//     * 账户_名称
//     */
//    @Column(name = "account_name")
//    private String accountName;
//
//    /**
//     * 账户_性别(字典表)：1-男、2-女
//     */
//    @Column(name = "account_gender")
//    private String accountGender;
//
//    /**
//     * 账户_出生日期
//     */
//    @Column(name = "account_birth_date")
//    private Date accountBirthDate;
//
//    /**
//     * 账户_手机号
//     */
//    @Column(name = "account_mobile")
//    private String accountMobile;
//
//    /**
//     * 账户_身份证号
//     */
//    @Column(name = "account_id_card")
//    private String accountIdCard;
//
//    /**
//     * 民族
//     */
//    @Column(name = "account_nation")
//    private String accountNation;
//
//    /**
//     * 户籍
//     */
//    @Column(name = "account_registered")
//    private String accountRegistered;
//
//    /**
//     * 婚姻
//     */
//    @Column(name = "account_marriage")
//    private String accountMarriage;
//
//    /**
//     * 学历
//     */
//    @Column(name = "account_education")
//    private String accountEducation;
//
//    /**
//     * 账户_职业
//     */
//    @Column(name = "account_profession")
//    private String accountProfession;
//
//    /**
//     * 账户_情况
//     */
//    @Column(name = "account_info")
//    private String accountInfo;
//
//    /**
//     * 账户_照片_主键数组：以 , 分隔
//     */
//    @Column(name = "account_image_ids")
//    private String accountImageIds;
//
//    /**
//     * 账户_是否删除：0-未删除、1-已删除
//     */
//    @Column(name = "account_deleted")
//    private String accountDeleted;
//
//
//    /**
//     * 创建_用户_主键
//     */
//    @Column(name = "create_user_id")
//    private String createUserId;
//
//    /**
//     * 创建_时间
//     */
//    @Column(name = "create_datetime")
//    private Date createDatetime;
//
//    /**
//     * 更新_用户_主键
//     */
//    @Column(name = "update_user_id")
//    private String updateUserId;
//
//    /**
//     * 更新_时间
//     */
//    @Column(name = "update_datetime")
//    private Date updateDatetime;
//
//}