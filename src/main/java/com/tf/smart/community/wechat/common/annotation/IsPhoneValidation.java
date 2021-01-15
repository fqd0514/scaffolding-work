package com.tf.smart.community.wechat.common.annotation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 功能描述：
 * 创建人：liutong
 * 创建日期：2020/10/14 14:09
 **/
public class IsPhoneValidation implements ConstraintValidator<IsPhone, String> {

    private static final Pattern PATTERN=Pattern.compile( "^1[345678]\\d{9}$");

    @Override
    public void initialize(IsPhone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isEmpty(value)) {
            return false;
        } else {
            return PATTERN.matcher(value).matches();
        }
    }
}
