package com.tf.smart.community.wechat.common.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.stereotype.Component;

/**
 * Validate 自定义方法校验工具类
 * @Author Leeyoung
 * @Date 2020/8/24
 **/
@Component
public class ValidateUtils {

	private ValidateUtils() {
	}

	// 线程安全的
	private static Validator validator;

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static <T> void validate(T t,Class<?> clazz) {
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t,clazz);
		if (constraintViolations.size() > 0) {
			StringBuilder validateError = new StringBuilder();
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				validateError.append(constraintViolation.getMessageTemplate()).append(";");
			}

			throw new ValidationException(validateError.toString());
		}
	}

	public static <T> void validate1(T t) {
		Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false) // 为true，则有一个错误就结束校验
				.buildValidatorFactory().getValidator();

		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

		StringBuilder validateError = new StringBuilder();

		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			validateError.append(constraintViolation.getMessage()).append(";");
		}

		throw new ValidationException(validateError.toString());
	}


}