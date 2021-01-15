package com.tf.smart.community.wechat.common.constraint;

import com.tf.smart.community.wechat.common.constraint.validator.PhoneNumberConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义电话号码约束条件
 *
 * @author 翟晶
 */
@Documented
@Constraint(validatedBy = {PhoneNumberConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(PhoneNumberConstraint.List.class)
public @interface PhoneNumberConstraint {
    String message() default "电话号码不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        PhoneNumberConstraint[] value();
    }
}
