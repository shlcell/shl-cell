package com.shl.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.shl.demo.utils.RedisUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
public class ShlController {

    @Resource
    private RedisUtils redisUtils;

    @PostMapping("/t")
    public String login() {
        String s="2023-03-16 15:49:00";
        System.out.println(s);


        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(format);
        redisUtils.set("date", "2023-03-16 15:49:00", 3L, TimeUnit.MINUTES);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",new Date());
        jsonObject.put("date","2023-03-16 15:49:00");

        redisUtils.set("11111111111", jsonObject, 3L, TimeUnit.MINUTES);
        Object test = redisUtils.get("11111111111");
        JSONObject jsonObject1 = (JSONObject) JSONObject.toJSON(test);
        System.out.println(jsonObject1);


        redisUtils.set("22222222222", jsonObject.toJSONString(), 3L, TimeUnit.MINUTES);
        Object sdf = redisUtils.get("22222222222");
        JSONObject jsonObject2 = JSONObject.parseObject(String.valueOf(sdf));
        System.out.println(jsonObject2);

        return "11";
    }


}
