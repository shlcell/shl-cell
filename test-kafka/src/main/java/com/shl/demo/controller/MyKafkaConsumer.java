package com.shl.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = MyKafkaProducer.TOPIC_TEST)
    public void consumerGroup(ConsumerRecord<?, ?> record) {
        Optional<?> message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            log.info("接收数据Message--{}", message);
        }
    }

//    @KafkaListener(topics = KafkaProducer.TOPIC_TEST)
//    public void consumerGroup(ConsumerRecord<?, ?> record, Acknowledgment ack) {
//        Optional<?> message = Optional.ofNullable(record.value());
//        if (message.isPresent()) {
//            ack.acknowledge();
//            log.info("接收数据Message--{}",message);
//        }
////        log.info("当前时间戳{}",DateTimeUtils.currentTimeMillis());
//    }

//    StringBuilder sss = new StringBuilder();
//
//    @KafkaListener(topics = KafkaProducer.TOPIC_TEST)
//    public void consumerGroup(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
//
//        Optional<?> messageK = Optional.ofNullable(record.key());
//        if (messageK.isPresent()) {
//            Object msgK = messageK.get();
//            // log.info("Message--" + msgK + ":" + msgV);
//            sss.append(msgK);
//            String substring = sss.substring(4);
//            if ("9999".equals(substring)) {
//                DateTime timeE = new DateTime();
//                long c2 = DateTimeUtils.currentTimeMillis();
//                log.info("接收消息完成时间：{}", timeE.toString("yyyy-MM-dd hh:mm:ss"));
//            } else {
//                sss.delete(0, sss.length() - 1);
//            }
//            // 手动提交偏移量
//            ack.acknowledge();
//        }
//    }

}