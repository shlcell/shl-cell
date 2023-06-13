package com.shl.demo.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @Author: Yanggc
 * DateTime: 11/17 11:04
 */
@Service
public class SmsServiceImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String nowDateTime = dateTimeFormatter.format(now);


    public String getVerifyCode(String phoneNumber) {
        String cacheKey = "appc_verifyCode_" + DateTimeFormatter.ofPattern("yyyyMMdd").format(now) + "_" + phoneNumber;

        if (redisTemplate.hasKey(cacheKey)) {
            Map<String, Object> cacheMap = (Map<String, Object>) redisTemplate.opsForValue().get(cacheKey);
            Integer sentTimes = (Integer) cacheMap.get("sentTimes");
            List<String> sendTimeList = (List<String>) cacheMap.get("sendTimeList");


            int sendTimeListSize = sendTimeList.size();

            /**
             *60S发送一次：最后一次发送时间距离现在不超过一分钟，返回发送次数太快
             */
            LocalDateTime lastSendTime = LocalDateTime.parse(sendTimeList.get(sendTimeListSize - 1), dateTimeFormatter);
            if (lastSendTime.plusSeconds(60).isAfter(now)) {
                return "overRate";
            }

            /**
             * 一小时最多发送5次，如果超过5次，取最后5次的“第一次”，判断时间距离现在是否超过一小时，如果超过一小时，提示过一个小时候再发送
             */

            if (sendTimeList.size() >= 5) {
                String lastFiveTimesFirst = sendTimeList.get(sendTimeListSize - 5);
                LocalDateTime lastFiveTimesFirstTime = LocalDateTime.parse(lastFiveTimesFirst, dateTimeFormatter);
                if (lastFiveTimesFirstTime.plusHours(1).isAfter(now)) {
                    return "overRateOneHour";
                }
            }

            //一天发送10次
            if (sentTimes > 10) {
                return "timesExceededOneDay";
            }

            /**
             * 更新cache操作:超过一分钟，并且是合法请求，重新生成验证码
             */
            String verifyCode = generateVerifyCode();

            Map<String, Object> resultCacheMap = new HashMap<>();
            resultCacheMap.put("sentTimes", sentTimes + 1);
            resultCacheMap.put("verifyCode", verifyCode);

            sendTimeList.add(nowDateTime);
            resultCacheMap.put("sendTimeList", sendTimeList);
            redisTemplate.boundValueOps(cacheKey).set(resultCacheMap, 24, TimeUnit.HOURS);

            return verifyCode;
        }


        dateTimeFormatter.format(now);

        Map<String, Object> verifyCodeMap = new HashMap<>();

        String verifyCode = generateVerifyCode();

        verifyCodeMap.put("verifyCode", verifyCode);
        verifyCodeMap.put("sentTimes", 1);
        List<String> dateTimeList = new ArrayList<>();
        dateTimeList.add(nowDateTime);
        verifyCodeMap.put("sendTimeList", dateTimeList);

        redisTemplate.boundValueOps(cacheKey).set(verifyCodeMap, 24, TimeUnit.HOURS);
        return verifyCode;
    }


    public String generateVerifyCode() {
        Integer randNum = (int) (Math.random() * (9999) + 1);
        String verifyCode = String.format("%04d", randNum);
        return verifyCode;
    }

}