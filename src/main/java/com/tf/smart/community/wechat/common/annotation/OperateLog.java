package com.tf.smart.community.wechat.common.annotation;

import lombok.Getter;

import java.lang.annotation.*;

/**
 * @author liutong
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLog {

    enum OperationTypeEnum {UNKNOWN,SELECT,DELETE,UPDATE,INSERT,EXPORT}

    enum LogTypeEnum {
        OPERATE(1), LOGIN(0);
        @Getter
        private int code;
        LogTypeEnum(int code){
            this.code = code;
        }
    }

    /**
     * 操作内容
     * @return
     */
    String operationContent() default "";

    /**
     * 操作类型(enum):主要是SELECT,INSERT,UPDATE,DELETE
     */
    OperationTypeEnum operationType() default OperationTypeEnum.UNKNOWN;

    /**
     * 日志类型(enum):主要是OPERATE,LOGIN
     */
    LogTypeEnum logTypeEnum() default LogTypeEnum.OPERATE;
}
