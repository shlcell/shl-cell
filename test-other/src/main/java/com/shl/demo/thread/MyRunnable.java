package com.shl.demo.thread;

/**
 * 实现Runnable接口也是一种创建线程的方式，它更加灵活，因为通过实现接口，可以避免单继承带来的局限性。
 * 实现Runnable接口时，需要重写run()方法，run()中的代码也将作为线程的执行体。
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        // 线程执行体
        System.out.println("线程:" + Thread.currentThread().getName() + " 实现Runnable接口 正在执行");
    }
}

