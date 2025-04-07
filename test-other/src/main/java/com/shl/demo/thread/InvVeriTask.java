package com.shl.demo.thread;

import java.util.concurrent.Callable;

public class InvVeriTask implements Callable<Boolean> {



    public InvVeriTask() {

    }

    @Override
    public Boolean call()  {
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            System.out.println("异常1111111");
//
//        }
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread().getName()+"-----------"+i);
        }
        return true;
    }
}
