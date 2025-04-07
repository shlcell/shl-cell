//package com.shl.demo.sms;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.stereotype.Service;
//
//import java.time.*;
//
//@Service
//public class SmsLoginService {
//
//    private static final String SMS_COUNT_KEY_PREFIX = "sms:count:";
//    private static final String SMS_LIMIT_ZSET_KEY = "sms:limit";
//
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    /**
//     * 发送短信登录验证码
//     *
//     * @param phone 手机号
//     */
//    public void sendSmsLoginCode(String phone) {
//        // 查询用户当天已发送的短信数量
//        ZSetOperations<String, Object> zSetOps = redisTemplate.opsForZSet();
//        double todayCount = zSetOps.count(SMS_LIMIT_ZSET_KEY + "phone", getTodayStart(), getTodayEnd());
//        if (todayCount >= 10) {
//            throw new RuntimeException("今天发送短信次数已达上限");
//        }
//
//        // 向有序集合中添加该用户的手机号，并将当前时间戳作为分值
//        zSetOps.add(SMS_LIMIT_ZSET_KEY, phone, System.currentTimeMillis());
//
//        // 同时向 Redis 计数器中记录该用户发送短信的总次数
//        ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();
//        String smsCountKey = SMS_COUNT_KEY_PREFIX + phone;
//        Long totalCount = valueOps.increment(smsCountKey, 1);
//        if (totalCount == 1) {
//            // 如果是第一次发送短信，则设置计数器的过期时间为一天
//            valueOps.set(smsCountKey, 1, Duration.ofDays(1));
//        }
//
//        // 发送短信验证码...
//    }
//
//    /**
//     * 获取今天凌晨的时间戳
//     */
//    private double getTodayStart() {
//        LocalDate today = LocalDate.now();
//        LocalDateTime todayStart = LocalDateTime.of(today, LocalTime.MIN);
//        return todayStart.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//    }
//
//    /**
//     * 获取今天晚上23:59:59的时间戳
//     */
//    private double getTodayEnd() {
//        LocalDate today = LocalDate.now();
//        LocalDateTime todayEnd = LocalDateTime.of(today, LocalTime.MAX);
//        return todayEnd.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//    }
//}
