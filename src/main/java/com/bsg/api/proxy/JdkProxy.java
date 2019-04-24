package com.bsg.api.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p></p>
 * 自己模拟JDK动态代理
 * @Author: dl.zhang
 * @Date: 2019/4/24
 */
public class JdkProxy implements InvocationHandler {

    private Object target ;//需要代理的目标对象

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理开始");
        Object result  = method.invoke(target,args);
        System.out.println("JDK动态代理结束");
        return result;

    }

    //定义获取代理对象方法
    private Object getJDKProxy(Object targetObject){
        //为目标对象target赋值
        this.target = targetObject;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();//实例化JDKProxy对象
        Hello user = (Hello) jdkProxy.getJDKProxy(new HelloImpl());//获取代理对象
        user.say("admin");//执行新增方法
    }
}
