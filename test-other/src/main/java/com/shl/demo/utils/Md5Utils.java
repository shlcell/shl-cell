package com.shl.demo.utils;

import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Md5Utils {

    /**
     * fpwybs 发票唯一标识
     *
     * @param invoiceCode   发票代码
     * @param invoiceNumber 发票号码
     * @param sdhm          数电号码
     * @param invoiceDate   开票日期
     * @param amount        金额
     * @return (( 发票代码 + 号码 / 数电号码拼接)md5)+开票时间+金额拼接)md5
     */
    public static String generateMd5(String invoiceCode, String invoiceNumber, String sdhm, Date invoiceDate, BigDecimal amount) throws NoSuchAlgorithmException {
        // 将日期格式化为 "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(invoiceDate);

        // 不含税金额保留两位小数
        String amountString = amount != null ? amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "";

        // 拼接发票信息
        String initialString;
        if (ObjUtil.isNotNull(sdhm)) {
            initialString = sdhm;
        } else {
            initialString = invoiceCode + invoiceNumber;
        }

        // 进行 MD5 加密
        String md5Hex = DigestUtil.md5Hex(initialString);
//        String md5Hex = md5Hex(initialString);

        // 拼接 MD5 值、开票日期和金额
        String finalString = md5Hex + dateString + amountString;

        // 计算最终的 MD5 值
//        return md5Hex(finalString);
        return DigestUtil.md5Hex(finalString);
    }

    private static String md5Hex(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        SELECT
//        MD5(
//                CONCAT(
//                        MD5(CONCAT('1D', '123456789')),
//                        '2024-08-13',
//                        '1234.56'
//                )
//        ) AS md5_value;
//        18d32f75602d2fde06ee1ef4ad2e8f41
//        18d32f75602d2fde06ee1ef4ad2e8f41
//        18d32f75602d2fde06ee1ef4ad2e8f41
        try {
            String invoiceCode = "1D";
            String invoiceNumber = "123456789";
            Date invoiceDate = new SimpleDateFormat("yyyy-MM-dd").parse("2024-08-13");
            BigDecimal amount = new BigDecimal("1234.56");

            String result = generateMd5(invoiceCode, invoiceNumber, null, invoiceDate, amount);
            System.out.println("MD5 Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}