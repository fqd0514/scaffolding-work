package com.tf.smart.community.wechat.common.constraint.validator;

import com.tf.smart.community.wechat.common.constraint.PhoneNumberConstraint;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 自定义电话号码约束条件验证器
 *
 * @author 翟晶
 */
public class PhoneNumberConstraintValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    private static final Pattern PATTERN_MOBILE_PHONE = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
    private static final Pattern PATTERN_TELE_PHONE_1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
    private static final Pattern PATTERN_TELE_PHONE_2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");

    @Override
    public void initialize(PhoneNumberConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        if (PATTERN_MOBILE_PHONE.matcher(value).matches() || PATTERN_TELE_PHONE_1.matcher(value).matches() || PATTERN_TELE_PHONE_2.matcher(value).matches()) {
            return true;
        }

        return false;
    }
}
