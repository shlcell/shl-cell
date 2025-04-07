package com.shl.demo.utils.excel;

import lombok.Data;

/**
 * 接口通用信息
 */
@Data
public class CommonInfo {

    /**
     * 接口编码
     */
    private String interface_code = "";
    /**
     * 请求示例
     */
    private String request_ex = "";
    /**
     * 返回示例
     */
    private String response_ex = "";
    /**
     * 备注
     */
    private String mark = "";
    /**
     * 接口名称
     */
    private String service_name = "";
    /**
     * 接口说明
     */
    private String content = "";
}
