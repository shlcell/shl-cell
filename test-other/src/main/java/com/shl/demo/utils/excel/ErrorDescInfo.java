package com.shl.demo.utils.excel;

import lombok.Data;

/**
 * 错误码信息
 */
@Data
public class ErrorDescInfo {

    private String code;
    private String type;
    private String desc;
    private String mark;
}
