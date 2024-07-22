package com.shl.demo.utils;

import lombok.Data;

import java.util.List;

/**
 * 年份、月票量、月金额
 */
@Data
public class SalesReportSalesYearNumbersAmounts {

    /**
     * 统计年份
     */
    private String year;
    /**
     * 月度票量，每月一条记录
     */
    private List<String> numbers;
    /**
     * 月度开票总金额，每月一条记录
     */
    private List<String> amounts;
}
