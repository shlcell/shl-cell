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
        String loanType = "1";
        if (1 == Integer.parseInt(loanType)) {
            System.out.println("1");
        }

        String str = "a,b,c,,";
        String[] ary = str.split(",");
        //预期大于 3，结果是 3
        System.out.println(ary.length);

        ArrayList<String> list = new ArrayList<String>();
        list.add("A");
        list.add("B");
        list.add("C");
        Object[] objects = list.toArray();
        String[] array = list.toArray(new String[2]);
        System.out.println("-----------------");
    }
}