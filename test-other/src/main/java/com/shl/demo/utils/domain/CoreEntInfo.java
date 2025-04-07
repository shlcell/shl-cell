package com.shl.demo.utils.domain;

import lombok.Data;

@Data
public class CoreEntInfo {
    private String coreEntName;     //企业名称
    private String coreEntOrgCode;  //统一社会信用代码
    /**
     * 行业 订单类型为05时必填
     */
    private String trade;

    public CoreEntInfo(String coreEntName, String coreEntOrgCode, String trade) {
        this.coreEntName = coreEntName;
        this.coreEntOrgCode = coreEntOrgCode;
        this.trade = trade;
    }
}