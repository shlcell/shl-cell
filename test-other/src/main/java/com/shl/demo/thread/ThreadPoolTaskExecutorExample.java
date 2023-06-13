package com.shl.demo.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 使用ThreadPoolTaskExecutor类创建了一个线程池对象，然后通过设置线程池的基本属性（核心线程数、最大线程数和任务队列容量），再将其初始化。
 * 接着，我们提交了20个任务到线程池中执行，并且通过Thread.sleep()方法模拟了一段业务处理时间，最后关闭了线程池。
 *
 * 在执行MyTask任务时，我们通过sleep()方法模拟每个任务需要运行2秒钟的时间，并且在任务执行时输出一些信息，方便观察。
 *
 * 需要注意的是，如果在关闭线程池时设置了waitForTasksToCompleteOnShutdown为true，则线程池会等待所有的任务执行完成后再进行关闭操作。
 * 这样可以确保所有任务都得到了充分的处理，避免因为线程池过早关闭导致任务未完成的情况。
 */
public class ThreadPoolTaskExecutorExample {

    public static void main(String[] args) throws InterruptedException {
        // 创建ThreadPoolTaskExecutor对象
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置线程池基本属性
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        // 线程池关闭时等待所有任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);

        // 初始化
        executor.initialize();

        // 提交任务到线程池
        for (int i = 1; i <= 20; i++) {
            executor.execute(new MyTask(i));
        }

        // 等待一段时间，模拟业务处理时间
        Thread.sleep(5000);

        // 关闭线程池
        executor.shutdown();
    }

    private static class MyTask implements Runnable {
        int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完成");
        }
    }
}
