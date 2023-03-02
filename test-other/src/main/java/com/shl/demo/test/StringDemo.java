package com.shl.demo.test;

import java.util.ArrayList;
import java.util.List;

public class StringDemo {
    public static void main(String[] args) {
        String s = "set/create/drop";
        // 将字符串按照指定符号拆分为数组
        String[] concat = s.split("/");
        List<String> arrayList = new ArrayList<>();
        for (String value : concat) {
            arrayList.add(value.toUpperCase());
        }
        System.out.println(arrayList);
    }
}