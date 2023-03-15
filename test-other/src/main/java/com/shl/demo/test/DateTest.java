package com.shl.demo.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        HashMap<Object, Object> map = new HashMap<>();

        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        map.put("date",format);
        System.out.println(map);
    }
}
