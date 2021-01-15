package com.tf.smart.community.wechat.common.utils;

import com.tf.smart.community.wechat.common.enums.CommonResponseEnum;
import com.tf.smart.community.wechat.common.exception.CommonBusinessException;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 功能描述：
 * 创建人：zhangjin
 * 创建日期：2020/8/13 11:32
 **/
public class DecimalUtils {

    public static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static String defaultFormat(BigDecimal decimal){
        if (null==decimal) {
            return "";
        }
        return DEFAULT_DECIMAL_FORMAT.format(decimal);
    }


    public static String formatDecimal(DecimalFormat format, BigDecimal decimal){
        if (null == format || null == decimal) {
            return "";
        }
        return format.format(decimal);
    }

    public static String formatDecimal(String formatPatten, BigDecimal decimal){
        if (StringUtils.isEmpty(formatPatten) || null == decimal) {
            return "";
        }
        try {
            DecimalFormat format = new DecimalFormat(formatPatten);
            return format.format(decimal);
        }catch (Exception e){
            throw new CommonBusinessException(CommonResponseEnum.OPERATION_FAILURE, String.format("格式化Decimal失败，patten = %s, decimal = %s", formatPatten, decimal.toString()));
        }
    }

    public static BigDecimal valueOfStr(String strNum, String errMessage){
        try{
            if (StringUtils.isEmpty(strNum)) {
                return null;
            }
            BigDecimal decimal = new BigDecimal(strNum.trim());
            return decimal;
        }catch (Exception e){
            throw new CommonBusinessException(CommonResponseEnum.INVALID_PARAMTER, errMessage);
        }
    }


}
