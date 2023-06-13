package com.shl.demo.thread;

import java.util.concurrent.*;

/**
 * 使用ThreadPoolExecutor类来创建线程池，指定了线程池的参数，包括核心线程数、最大线程数、空闲线程存活时间、阻塞队列容量、线程工厂和饱和策略。
 * 同时，我们也实现了一个简单的任务类MyTask，用来模拟多个任务的执行。
 * <p>
 * 在代码中，我们通过循环提交20个任务到线程池中，并最终关闭线程池。
 * 当线程池中的线程数超过核心线程数时，新提交的任务会先被放入阻塞队列中，等待核心线程处理完成后再进行处理。
 * 如果阻塞队列已满，则会创建新的线程执行任务，直到达到最大线程数。
 * 如果此时仍然有新的任务提交，会根据饱和策略进行处理，我们这里使用了AbortPolicy策略，即直接抛出异常。
 * <p>
 * 通过这种方式创建线程池，可以明确线程池的运行规则，避免资源耗尽的风险。
 *
 *
 * 线程池的饱和策略主要有以下几种：
 *
 * AbortPolicy（默认饱和策略）：当线程池队列已满并且线程池中的线程数量已达到最大值时，直接抛出RejectedExecutionException异常，拒绝新任务的提交。
 *
 * CallerRunsPolicy：当线程池队列已满并且线程池中的线程数量已达到最大值时，新提交的任务将由提交任务的线程来执行（即当前线程来执行）。
 *
 * DiscardOldestPolicy：当线程池队列已满且线程池中的线程数量已达到最大值时，会抛弃队列头部的任务，然后将新提交的任务加入队列中。
 *
 * DiscardPolicy：当线程池队列已满且线程池中的线程数量已达到最大值时，直接抛弃新提交的任务，不做任何处理。
 *
 * 除此之外，在JDK7及以上版本还新增了一个自定义饱和策略类ThreadPoolExecutor.AbortPolicy()，它可以在构造ThreadPoolExecutor对象时传入并进行使用。
 *
 * 这些饱和策略可以通过调用ThreadPoolExecutor或ThreadPoolTaskExecutor的setRejectedExecutionHandler()方法来设置。需要根据具体场景来选择合适的饱和策略，以保证线程池的正常运行并提升程序性能。
 */
public class ThreadPoolExecutorExample {

    public static void main(String[] args) {
        // 线程池基本参数：核心线程数、最大线程数、空闲线程存活时间、阻塞队列容量、线程工厂、饱和策略
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 提交任务到线程池
        for (int i = 1; i <= 20; i++) {
            threadPool.execute(new MyTask(i));
        }

        // 关闭线程池
        threadPool.shutdown();
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
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完成");
        }
    }
}
