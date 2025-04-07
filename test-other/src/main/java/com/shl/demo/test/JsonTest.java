package com.shl.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class JsonTest {
    public static void main(String[] args) {
        String s="{\"code\":0,\"data\":{\"result\":{\"orderSn\":\"bf3d399017164ccd9fae0d7c0ceaf0cb\",\"appId\":\"yc_215fa34f049e4df59954fb679b4ef8ae\",\"url\":\"http://xsdt1.i-xinnuo.com/cj_m/#/multiroleNoPhone/yc?orderSn=bf3d399017164ccd9fae0d7c0ceaf0cb\"}},\"msg\":\"操作成功！\",\"reqtime\":1735542597960}";
        String s1 = JSON.toJSONString(s);
        String s2 = s1.replace("\"", "\\\"");
        String s3 = s2.replace("\\\\", "\\\\\\");
        log.info(s1);
        log.info(s2);
        log.info(s3);
//        JSONObject jsonObject = new JSONObject();
//        Date date = new Date();
//
//
//        String formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
//        jsonObject.put("formatDate", "2023-03-17 12:31:52");
//        System.out.println("1-----" + jsonObject.get("formatDate"));
//
//
//        Date xxSuccessTime = jsonObject.getDate("formatDate");
//        String stringDate = jsonObject.getString("formatDate");
//        jsonObject.put("xxSuccessTime", xxSuccessTime);
//        System.out.println("2-----" + xxSuccessTime);
//        System.out.println("3-----" + stringDate);
    }
}
