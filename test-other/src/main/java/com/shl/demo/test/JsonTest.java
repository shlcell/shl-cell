package com.shl.demo.test;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonTest {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        Date date = new Date();


        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        jsonObject.put("formatDate", "2023-03-17 12:31:52");
        System.out.println("1-----" + jsonObject.get("formatDate"));


        Date xxSuccessTime = jsonObject.getDate("formatDate");
        String stringDate = jsonObject.getString("formatDate");
        jsonObject.put("xxSuccessTime", xxSuccessTime);
        System.out.println("2-----" + xxSuccessTime);
        System.out.println("3-----" + stringDate);
    }
}
