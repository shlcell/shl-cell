package com.shl.demo.thread;

import lombok.extern.slf4j.Slf4j;
import org.dromara.dynamictp.core.executor.DtpExecutor;
import org.dromara.dynamictp.core.support.ThreadPoolBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ThreadInvoke {

    public static void main(String[] args) {
        DtpExecutor zxbgTaxExecutor = ThreadPoolBuilder.newBuilder()
                .threadPoolName("zxbgTaxExecutor")
                .threadFactory("tax-common")
                .corePoolSize(1)
                .maximumPoolSize(1)
                .keepAliveTime(400)
                .timeUnit(TimeUnit.SECONDS)
                .workQueue("MemorySafeLinkedBlockingQueue", 2)
                .buildDynamic();

        List<Future<Boolean>> futures = null;
        AtomicInteger count = new AtomicInteger();
        ArrayList<Object> list = new ArrayList<>();
        List<InvVeriTask> invVeriTasks = Arrays.asList(new InvVeriTask(), new InvVeriTask(),
                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
//                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
//                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
//                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
//                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
//                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
//                new InvVeriTask(), new InvVeriTask(), new InvVeriTask(),
                new InvVeriTask());
        try {
            futures = zxbgTaxExecutor.invokeAll(invVeriTasks);
        } catch (InterruptedException e) {
            log.error("执行任务失败-startInvVeri-invokeAll {}", e.getMessage());
            System.out.println("yichang");
        } catch (Exception e) {
            System.out.println("xxxxxxx");
        }
        futures.forEach(future -> {
            try {
                if (future.get()) {
                    count.getAndIncrement();
                }
            } catch (Exception e) {
                log.error("执行任务失败-startInvVeri-get {}", e.getMessage());
            }
        });
    }
}
