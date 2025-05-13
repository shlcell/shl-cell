package com.shl.demo.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SystemException extends RuntimeException {
    private String code;
    private String message;

    public SystemException() {
        super();
    }

    public SystemException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SystemException(String code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }
}
