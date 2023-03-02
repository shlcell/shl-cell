package com.shl.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ipv4 地址正则表达式
 */
public class RegexMatches {

    public static void main(String[] args) {
        String str = "255.255.155.43";
        String pattern = "((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})(\\.((2(5[0-5]|[0-4]\\d))|[0-1]?\\d{1,2})){3}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
