package com.shl.testdynamictp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RestController
@SuppressWarnings("all")
public class TestController {

    @Resource
    private ThreadPoolExecutor dtpExecutor1;


    @GetMapping("/test")
    public String test() throws InterruptedException {
        dtpExecutor1.execute(() -> {
            log.info("i am tax-common task");
        });
        return "success";
    }
}
