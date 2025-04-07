package com.shl.testdynamictp.config;

import org.dromara.dynamictp.core.support.DynamicTp;
import org.dromara.dynamictp.core.support.ThreadPoolBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.dromara.dynamictp.common.em.QueueTypeEnum.MEMORY_SAFE_LINKED_BLOCKING_QUEUE;

@Configuration
public class ThreadPoolConfig {

    @DynamicTp("jucThreadPoolExecutor")
    @Bean
    public ThreadPoolExecutor jucThreadPoolExecutor() {
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    }

    @Bean
    public ThreadPoolExecutor zxbgTaxExecutor() {
        return ThreadPoolBuilder.newBuilder()
                .threadPoolName("zxbgTaxExecutor")
                .threadFactory("tax-common")
                .corePoolSize(10)
                .maximumPoolSize(20)
                .keepAliveTime(400)
                .timeUnit(TimeUnit.SECONDS)
                .workQueue(MEMORY_SAFE_LINKED_BLOCKING_QUEUE.getName(), 1000)
                .buildDynamic();
    }
}
