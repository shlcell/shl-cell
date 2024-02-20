package com.shl.test;

import org.junit.Test;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.util.*;

public class OtherTest2 {

    @Test
    public void json() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("2", 312321);
        jsonObject.put("1", 312321);
        String s = JSON.toJSONString(jsonObject);
//        System.out.println(s);

        HashMap<Object, Object> map = new HashMap<>();
        HashMap<Object, Object> map1 = new HashMap<>();
        map.put("3", 3);
        map1.put("4", jsonObject);
        map.put("date", map1);
        Object o = (Object) map;
        JSONObject jsonObject1 = new JSONObject((Map) o);
        jsonObject1.put("5", 5);
        String s1 = JSON.toJSONString(jsonObject1);
        System.out.println(s1);
        String date = jsonObject1.getString("date");
        System.out.println(date);
    }

    // 创建一个Integer类型的线程局部变量
    private static final ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();

    @Test
    public void ThreadLocal() {
        // 创建两个线程，分别向ThreadLocal变量中存入不同的值
        Thread threadOne = new Thread(() -> {
            threadLocalValue.set(1);
            print("ThreadOne");
            threadLocalValue.remove(); // 移除当前线程的ThreadLocal值，避免内存泄漏
        });

        Thread threadTwo = new Thread(() -> {
            threadLocalValue.set(2);
            print("ThreadTwo");
            threadLocalValue.remove(); // 移除当前线程的ThreadLocal值，避免内存泄漏
        });

        // 启动线程
        threadOne.start();
        threadTwo.start();
    }

    private static void print(String threadName) {
        // 在当前线程中获取ThreadLocal中的值
        Integer value = threadLocalValue.get();
        System.out.println(threadName + ": " + value);
    }

    public int powerOfThree(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 3;
        }
        return result;
    }

    @Test
    public void dss() {
        JSONObject jsonObject = new JSONObject();
        List<Integer> list = new ArrayList<>();
        list.add(130000);
        list.add(140000);
        jsonObject.put("areaInfo", list);
        List<String> areaInfo = jsonObject.getList("areaInfo", String.class);

        boolean contains = list.contains("130000");
        boolean contains1 = areaInfo.contains("130000");
        System.out.println(contains);
        System.out.println(contains1);

    }

    @Test
    public void str() {
        String s = "fds.fds.fsd";
        s = s.replaceAll("\\.", "-");
        System.out.println(s);
    }
}
