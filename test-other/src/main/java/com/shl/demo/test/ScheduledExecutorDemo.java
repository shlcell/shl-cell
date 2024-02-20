package com.shl.demo.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService 是一个能够支持按时间调度任务执行的线程池，它是 ExecutorService 接口的子接口。
 * 通过使用 ScheduledExecutorService，可以方便地实现周期性执行、定时执行、延迟执行等功能。
 * <p>
 * schedule(Runnable command, long delay, TimeUnit unit)：延迟指定时间后执行一次任务。
 * schedule(Callable<V> callable, long delay, TimeUnit unit)：延迟指定时间后执行一次有返回值的任务。
 * scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)：延迟指定时间后开始周期性地执行任务，每隔一段时间重复执行，直到被取消。
 * scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)：延迟指定时间后开始周期性地执行任务，每次执行完成后会延迟指定时间再执行下一次任务，直到被取消。
 * 需要注意的是，ScheduledExecutorService 在执行任务时使用的线程池是可配置的，所以可以针对不同的应用场景选择不同的线程池大小和参数设置，以达到更好的性能和效果。
 * <p>
 */
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
