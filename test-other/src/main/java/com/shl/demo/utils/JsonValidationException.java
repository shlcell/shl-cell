package com.shl.demo.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.ConstraintViolation;
import java.util.HashSet;
import java.util.Set;

/**
 * 入参校验错误异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JsonValidationException extends SystemException {
    private Set<ConstraintViolation<?>> violations;

    public JsonValidationException(String code, String message, Set<? extends ConstraintViolation<?>> violations) {
        super(code, message);
        if ( violations == null ) {
            this.violations = null;
        }
        else {
            this.violations = new HashSet<>( violations );
        }
    }

//    public JsonValidationException(String code, String message, Set<? extends ConstraintViolation<?>> violations, Throwable cause) {
//        super(code, message, cause);
//        if ( violations == null ) {
//            this.violations = null;
//        }
//        else {
//            this.violations = new HashSet<>( violations );
//        }
//    }
}
