package com.shl.demo.test;

import cn.hutool.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
//        list.add(null);
        list.add("1");
        list1.add("1");
//        list.add(getObject(list));
        System.out.println(list.size());
        List<String> collect = list1.stream()
                .filter(list::contains)
                .collect(Collectors.toList());
        System.out.println(collect.size());

    }

    private static List getObject(List list) {
        JSONObject json = new JSONObject();
        json.putOnce("test", "value");
        list.add(json);
        return list;
    }
}
