package com.zhouxin.training.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ProxyTest这是个动态代理类
 *
 * @author 周鑫
 * create on 2018/08/02
 */
public class ProxyTest implements InvocationHandler
{
    /**
     * 每个动态代理类都必须要实现InvocationHandler这个接口，并且每个代理类的实例都关联到了一个Handler
     * 当我们通过代理对象调用一个方法的时候，这个方法的调用就会被转发为由InvocationHandler这个接口invoke方法来进行调用
     *
     * @param proxy  指代我们所代理的那个真实对象
     * @param method 指代的是我们所要调用真实对象的某个方法的method对象
     * @param args   指代的是调用真实对象某个方法时接受的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        return null;
    }
}
