package com.shl.demo.test;

import cn.hutool.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListTest {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add(getObject(list));
        System.out.println(list.size());

    }

    private static List getObject(List list) {
        JSONObject json = new JSONObject();
        json.putOnce("test", "value");
        list.add(json);
        return list;
    }
}
