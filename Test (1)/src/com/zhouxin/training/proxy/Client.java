package com.zhouxin.training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client
{
    public static void main(String[] args)
    {
        // 我们要代理的真实对象
        Subject realSubject = new RealSubject();
        // 我们要代理那个真实对象，就将该对象传进去，最后时通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);

        /**
         * Proxy这个类的作用就是用来动态创建一个代理对象的类。
         * newProxyInstance这个方法就是得到一个动态的代理对象。
         *
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader(), 我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数 realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数 handler，我们这里将这个代理对象关联到了上方的InvocationHandler这个对象上
         */
        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
    }
}
