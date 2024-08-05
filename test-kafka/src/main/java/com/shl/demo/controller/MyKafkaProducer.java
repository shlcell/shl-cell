package com.shl.demo.controller;

import com.shl.demo.entity.Student;
import com.shl.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
public class MyKafkaProducer {

    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Resource
    private StudentService service;

    public static final String TOPIC_TEST = "test";

    /**
     * kafka推送消息
     */
    @RequestMapping("/pushStudent")
    public void pushMessage() {
        List<Student> list = service.createD();
        long start = DateTimeUtils.currentTimeMillis();
        for (Student student : list) {
            ListenableFuture<SendResult<Object, Object>> send = kafkaTemplate.send(TOPIC_TEST, student.toString());
            send.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.error(TOPIC_TEST + "-生产者发送消息失败" + throwable.getMessage());
                }

                @Override
                public void onSuccess(@Nullable SendResult<Object, Object> stringObjectSendResult) {
                    // log.info(TOPIC_TEST + "-生产者发送消息成功" + stringObjectSendResult.toString());
                }
            });
        }
        long end = DateTimeUtils.currentTimeMillis();
        log.info("发送时间消耗{}", end - start);
    }

    /**
     * kafka推送消息
     */
    @PostMapping(value = "/pushMessage/{message}")
    public void pushMessage(@PathVariable String message) {
        DateTime timeS = new DateTime();
        long c1 = DateTimeUtils.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        int a = 100000;
        // 发送消息
        for (int i = 0; i < a; i++) {
            stringBuilder.append(i);
            ListenableFuture<SendResult<Object, Object>> send = kafkaTemplate.send(TOPIC_TEST, stringBuilder.toString(), message);
            send.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.error(TOPIC_TEST + "-生产者发送消息失败" + throwable.getMessage());
                }

                @Override
                public void onSuccess(@Nullable SendResult<Object, Object> stringObjectSendResult) {

                    // log.info(TOPIC_TEST + "-生产者发送消息成功" + stringObjectSendResult.toString());
                }
            });
            stringBuilder.delete(3, stringBuilder.length());
            if (i == a - 1) {
                DateTime timeE = new DateTime();
                long c2 = DateTimeUtils.currentTimeMillis();
                log.info("发送起始时间：{}--发送消息完成,完成时间：{}--发送信息消耗时间：{}",
                        timeS.toString("yyyy-MM-dd hh:mm:ss"), timeE.toString("yyyy-MM-dd hh:mm:ss"), c2 - c1);
//                log.info("开始发送时间:{}" + "发送消息完成,完成时间：{}", timeS.toString("yyyy-MM-dd hh:mm:ss"), timeE.toString("yyyy-MM-dd hh:mm:ss"));
            }
        }
    }
}