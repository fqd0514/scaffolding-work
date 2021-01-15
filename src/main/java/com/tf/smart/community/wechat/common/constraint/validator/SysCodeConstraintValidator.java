package com.tf.smart.community.wechat.common.constraint.validator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tf.smart.community.wechat.common.constraint.SysCodeConstraint;
import com.tf.smart.community.wechat.common.utils.CacheUtil;
import com.tf.smart.community.wechat.entity.vo.SysCodeVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static com.tf.smart.community.wechat.common.constant.CommonConstant.REDIS_SEPARATOR;
import static com.tf.smart.community.wechat.common.constant.CommonConstant.REDIS_SYSCODE;

public class SysCodeConstraintValidator implements ConstraintValidator<SysCodeConstraint, String> {
    @Autowired
    private CacheUtil cacheUtil;

    private List<SysCodeVO> sysCodeVOList;

    @Override
    public void initialize(SysCodeConstraint constraintAnnotation) {
        sysCodeVOList = JSON.parseObject(String.valueOf(cacheUtil.get(REDIS_SYSCODE + REDIS_SEPARATOR + constraintAnnotation.codeName())), new TypeReference<List<SysCodeVO>>() {
        });
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        if (CollectionUtils.isNotEmpty(sysCodeVOList)) {
            for (SysCodeVO sysCodeVO : sysCodeVOList) {
                if (StringUtils.equals(sysCodeVO.getCodeValue(), value)) {
                    return true;
                }
            }
        }

        return false;
    }
}
