package com.shl.demo.test;

import com.google.gson.GsonBuilder;
import org.dromara.dynamictp.common.util.CommonUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TempTest {

    public static void main(String[] args) {
//        String bb = "456";
//        a(bb);
//        System.out.println(bb);
//        Cat cat = new Cat();
//        BigDecimal bigDecimal = BigDecimal.valueOf(cat.getName());
//        System.out.println(bigDecimal);

        LocalDate yesterday = LocalDate.now();
        String formattedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(formattedDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        String startTime = sdf.format(calendar.getTime());
        System.out.println(startTime);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, -1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        String startTime1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(calendar1.getTime()); // 2025-04-20 00:00:00
        System.out.println(startTime1);


        // 获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // 获取当天结束时间（23:59:59）
        LocalDateTime endOfDay = localDateTime.toLocalDate().atTime(23, 59, 59);
        // 计算过期时间
        Duration duration = Duration.between(endOfDay, endOfDay);
        if (duration.isNegative() || duration.isZero()) {
            System.out.println("已过期");
        }
        System.out.println(duration);

//        int i = calculateSize(65);
//        int i1 = calculateSize(129);
//        System.out.println(i);
//        System.out.println(i1);
//
//
//        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
//        Integer index=2;
//        list.remove(index);
//        System.out.println(list);

        String a = "123";
        int i = a.indexOf("1");
        System.out.println(i);

        List resultList = (List) gsonToObject(null, List.class);
        System.out.println(resultList);

        String dateText = "2023-04-39";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate date = LocalDate.parse(dateText, formatter);
            System.out.println("日期格式正确: " + date);
        } catch (DateTimeParseException e) {
            System.out.println("日期格式错误");
        }

    }

    private static void a(String a) {
        a = "123";
    }

    static class Cat {
        private Double name;

        public double getName() {
            return name;
        }
    }


    private static int calculateSize(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }

    public static Object gsonToObject(String gsonStr, Class<?> clazz) {
        return new GsonBuilder().disableHtmlEscaping().create().fromJson(gsonStr, clazz);
    }
}
