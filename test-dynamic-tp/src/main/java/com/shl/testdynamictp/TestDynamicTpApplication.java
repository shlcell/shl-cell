package com.shl.testdynamictp;

import org.dromara.dynamictp.core.spring.EnableDynamicTp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicTp
public class TestDynamicTpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestDynamicTpApplication.class, args);
    }

}
