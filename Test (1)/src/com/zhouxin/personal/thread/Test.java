package com.zhouxin.personal.thread;

public class Test
{
    public static void main(String[] args)
    {
        // 第一种
//        Thread thread = new Thread(new SingleThread());
        //第二种
        SingleThread s = new SingleThread();
        s.start();
    }
}
