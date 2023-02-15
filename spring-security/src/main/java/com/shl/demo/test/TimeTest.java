package com.shl.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTest {

    // DAY_TIME_STAMP = 1000*60*60*24
    public static final long DAY_TIME_STAMP = 86400000;

    public static void main(String[] args) throws ParseException {
        //时间戳转为日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long timeMillis = System.currentTimeMillis();
        String timeStamp = "1664341652565";
        long time = Long.parseLong(timeStamp);
        Date date = new Date();
        date.setTime(time);
        System.out.println("时间戳转日期格式：" + simpleDateFormat.format(date));
        //日期格式转时间戳
        String dataTime = "2021-01-01 00:00:00";
        Date date1 = simpleDateFormat.parse(dataTime);
        long time1 = date1.getTime();
        System.out.println("日期格式转时间戳：" + time1);

        System.out.println("=======" + getExpireTime());
        System.out.println("=======" + getExpireTimeStamp());
    }

    /**
     * 获取过期时间：当前时间戳 + 1天
     * @return string
     */
    public static String getExpireTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + DAY_TIME_STAMP;
        Date date = new Date();
        date.setTime(expireTime);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取过期时间戳：当前时间戳 + 1天
     * @return string
     */
    public static long getExpireTimeStamp() {
        long currentTime = System.currentTimeMillis();
        return currentTime + DAY_TIME_STAMP;
    }
}
