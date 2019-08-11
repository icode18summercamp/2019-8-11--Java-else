package com.zhouxin.personal;

import com.zhouxin.personal.thread.ThreaTest;

/**
 * @author 谭强
 * @date 2019/8/10
 */
public class TestThread5 extends Thread {
//public class TestThread5 implements Runnable {
    static private Integer count = 0;

    private String name;
    public TestThread5(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程运行开始 ");

        for (int i = 0; i < 1000; i++) {
            count++;
        }

        System.out.println(Thread.currentThread().getName() + "线程运行结束");
    }


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "主线程运行开始!");

        TestThread5 mTh1 = new TestThread5("A");
        TestThread5 mTh2 = new TestThread5("B");

        mTh1.start();
        mTh2.start();

        try{
            mTh1.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try{
            mTh2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("count: " + count);
        System.out.println(Thread.currentThread().getName() + "主线程运行结束!");
    }
}