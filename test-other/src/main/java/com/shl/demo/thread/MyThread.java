package com.shl.demo.thread;

/**
 * 继承Thread类是Java中最基本的创建线程的方式。
 * 需要定义一个类继承Thread类，并重写run()方法，run()中的代码将作为线程的执行体。
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        // 线程执行体
        System.out.println("线程:" + Thread.currentThread().getName() + " 继承Thread类 正在执行");
    }
}