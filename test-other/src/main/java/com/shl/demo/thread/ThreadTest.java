package com.shl.demo.thread;

import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 继承Thread类------------------------------------------------------------------------------
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();
        thread1.start();
        thread2.start();
        threadPool.execute(thread1);
        threadPool.execute(thread2);

        // 实现Runnable接口---------------------------------------------------------------------------
        MyRunnable runnable1 = new MyRunnable();
        MyRunnable runnable2 = new MyRunnable();

        Thread thread3 = new Thread(runnable1);
        Thread thread4 = new Thread(runnable2);

        thread3.start();
        thread4.start();
        threadPool.execute(thread3);
        threadPool.execute(thread4);

        // 实现Callable接口--------------------------------------------------------------------------------
        MyCallable callable1 = new MyCallable();
        MyCallable callable2 = new MyCallable();

        // 线程池不允许使用 Executors 去创建(阿里开发手册)，而是通过 ThreadPoolExecutor 的方式，这样
        // 的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> result1 = threadPool.submit(callable1);
        Future<String> result2 = threadPool.submit(callable2);

        System.out.println(result1.get() + "-------------");
        System.out.println(result2.get() + "-------------");
        // 关闭线程池
        threadPool.shutdown();
        try {
            // 等待所有任务执行完成
            boolean result = threadPool.awaitTermination(1, TimeUnit.MINUTES);
            if (!result) {
                // 调用shutdownNow()方法
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            // 调用shutdownNow()方法
            threadPool.shutdownNow();
        }

    }
}
