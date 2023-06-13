package com.shl.demo.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorDemo {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                int i = 1 / 0;
                System.out.println("Hello, world!");
            } catch (Exception e) {
                System.err.println("Task failed to execute: " + e.getMessage());
            }
        };

        int initialDelay = 0;
        int period = 1;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        executor.scheduleAtFixedRate(task, initialDelay, period, timeUnit);
    }
}
