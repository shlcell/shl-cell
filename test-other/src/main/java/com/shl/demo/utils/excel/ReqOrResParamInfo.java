package com.shl.demo.utils.excel;

import lombok.Data;

/**
 * 请求或响应字段信息
 */
@Data
public class ReqOrResParamInfo {

    private String name;
    private String type;
    private String required_fields;
    private String length;
    private String desc;
    private String mark;
    private String parentId;
    private String id;
}
