package com.zhouxin.personal.thread;

// public class SingleThread implements Runnable{
public class SingleThread extends Thread {
    @Override
    public void run() {
        int a = 1 + 1;
        System.out.println("这是一个线程.......");
    }
}
