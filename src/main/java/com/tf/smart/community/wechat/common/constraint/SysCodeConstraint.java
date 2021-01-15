package com.tf.smart.community.wechat.common.constraint;

import com.tf.smart.community.wechat.common.constraint.validator.SysCodeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义系统编码表约束条件
 *
 * @author 翟晶
 */
@Documented
@Constraint(validatedBy = {SysCodeConstraintValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(SysCodeConstraint.List.class)
public @interface SysCodeConstraint {
    String codeName();

    String message() default "系统编码表键值对不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        SysCodeConstraint[] value();
    }
}
