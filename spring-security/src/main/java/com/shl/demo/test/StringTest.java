package com.shl.demo.test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class StringTest {
    public static void main(String[] args) throws Exception {

        String i="java中国";

        // 编码
        System.out.println(Arrays.toString(i.getBytes()));
        System.out.println(i.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(Arrays.toString(i.getBytes("gbk")));
        System.out.println(i.getBytes("gbk").length);
        System.out.println(Arrays.toString(i.getBytes("utf_32")));
        System.out.println(i.getBytes("utf_32").length);

        // 解码
        String s = new String(i.getBytes());
        System.out.println(s);
        String s1 = new String(i.getBytes("gbk"));
        System.out.println(s1);
    }
}
