package com.shl.demo.test;

import com.alibaba.fastjson.JSONObject;

public class JsonTest {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1",1);
        jsonObject.put("1",2);
        System.out.println(jsonObject.get("1"));
    }
}
