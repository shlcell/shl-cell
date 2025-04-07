package com.shl.demo.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.aspectj.weaver.ast.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class IntegerTest {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal("");
//        Test test = new Test();
////        test.setGxzxcybs("1");
//        Integer gxzxcybs = Integer.valueOf(test.getGxzxcybs());
//
//        if (null != gxzxcybs && gxzxcybs.equals(1)) {
//            System.out.println("111");
//        }

        DateTime dateTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), -3));

        LocalDate now = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String getkpjl ="20240815";
        String newDate = getkpjl.substring(0, getkpjl.length() - 2) + "01";
        LocalDate sckpsjTime = LocalDate.parse(newDate, dtf).plusMonths(4);
        boolean b = sckpsjTime.isBefore(now) || sckpsjTime.isEqual(now);

//        String getkpjl = "20240815";  // 假设这是输入日期字符串
//
//        // 定义日期格式
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//
//        // 将 getkpjl 转换为 LocalDate 对象
//        LocalDate inputDate = LocalDate.parse(getkpjl, formatter);
//
//        // 获取当前日期
//        LocalDate currentDate = LocalDate.now();
//
//        // 获取 90 天前的日期
//        LocalDate ninetyDaysAgo = currentDate.minusDays(90);
//
//        if (inputDate.isBefore(ninetyDaysAgo) || inputDate.isAfter(currentDate)) {
//            System.out.println("输入日期不在过去的 90 天内");
//        } else {
//            System.out.println("输入日期在过去的 90 天内");
//        }
    }

    @Data
    private static class Test {
        String gxzxcybs;
    }
}
