package com.shl.demo.thread;

import java.util.concurrent.Callable;

/**
 * 实现Callable接口也可以用来创建线程，它与Runnable接口类似，也是通过重写call()方法来定义线程的执行体，并且可以返回执行结果。
 * 使用Callable接口创建线程时，需要将其提交给线程池进行执行，并通过返回的Future对象获取执行结果。
 * 因为Callable接口可以返回结果，所以线程池可以方便地管理和控制线程的执行。
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        // 线程执行体
        return "线程:" + Thread.currentThread().getName() + " 实现Callable接口 正在执行";
    }
}

