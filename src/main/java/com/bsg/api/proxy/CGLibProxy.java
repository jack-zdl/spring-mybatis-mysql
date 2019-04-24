package com.bsg.api.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 代理类
 */
public class CGLibProxy implements MethodInterceptor {

    private Object target;//需要代理的目标对象

    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls,this); //指定父类,设置回调,创建并返回代理对象
    }

    //定义获取代理对象方法
    public Object getCglibProxy(Object objectTarget){
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        enhancer.setCallback(this);// 设置回调
        Object result = enhancer.create();//创建并返回代理对象
        return result;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        System.out.println("---------------CGLib代理前---------------------");
        Object result = proxy.invokeSuper(o,objects);
        System.out.println("---------------CGLib代理后---------------------");
        return result;
    }

    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        helloProxy.say("jack");

        Hello user =  (Hello) cgLibProxy.getCglibProxy(new HelloImpl());//获取代理对象
        helloProxy.say("cglibjack");
    }
}
