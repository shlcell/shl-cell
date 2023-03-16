package com.shl.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DateTest {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        HashMap<Object, Object> map = new HashMap<>();
        String s = date.toString();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong("1678863452000")));
        String format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

//        map.put("date",format);
        System.out.println(format1);
        System.out.println(s);
    }
}
