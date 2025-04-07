package com.shl.demo.sqzxbg;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StandardVo {
    /**
     * 接口ID 接口编码
     */
    private String interfaceCode;
    /**
     * 平台商编码
     */
    private String ptsbm;
    /**
     * 纳税人识别号
     */
    private String nsrsbh;
    /**
     * 查询月份数
     */
    private String cxyfs;
    /**
     * 第三方授权协议ID
     */
    private String dsfcjbm;

    /**
     * 第三方接口识别码
     */
    private String byzd1;
}
