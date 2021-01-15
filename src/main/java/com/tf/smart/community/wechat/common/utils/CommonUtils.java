package com.tf.smart.community.wechat.common.utils;

import java.math.BigDecimal;

/**
 * 功能描述：
 * 创建人：zhangjin
 * 创建日期：2020/9/1 14:24
 **/
public class CommonUtils {


    /**
     * 左补零
     * @param num
     * @param totalSize
     * @return
     */
    public static String leftPadZero(Long num, int totalSize){
        if (null == num) {
            return null;
        }
        String strNum = num.toString();
        if (strNum.length() >= totalSize) {
            return strNum;
        }
        int zeroNum = totalSize - strNum.length();
        StringBuilder sb = new StringBuilder();
        String strZero = BigDecimal.ZERO.toString();
        while (zeroNum >0){
            sb.append(strZero);
            zeroNum--;
        }
        sb.append(strNum);
        return sb.toString();
    }
}
