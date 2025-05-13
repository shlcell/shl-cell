package com.shl.demo.utils;

import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;


/**
 * 入参验证工具
 *
 * @author zhaoliang
 */
public class ValidatorUtil {
    private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    public static <T> void check(T vo) {
        Set<ConstraintViolation<T>> violationSet = validator.validate(vo);
        if (!violationSet.isEmpty()) {
            throw new JsonValidationException("999", "11", violationSet);
        }
    }

    /**
     * 方法描述：校验对象数据内容
     **/
    public static <T> String validationEntity(T entity){
        //错误信息
        StringBuffer result = new StringBuffer();
        //校验
        // ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        // Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(entity);
        if(constraintViolations!=null && constraintViolations.size()>0){
            //  result.append("第"+idx+"行：<br/>");
            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                result.append(constraintViolation.getMessage() + ";");
            }
        }
        return result.toString();
    }
}
