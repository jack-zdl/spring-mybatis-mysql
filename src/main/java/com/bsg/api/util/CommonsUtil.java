package com.bsg.api.util;

import java.util.concurrent.*;

public class CommonsUtil {

//    private  static ExecutorService excutors =Executors.newFixedThreadPool(3);
    private static int corePoolSize = 3;
    private static int maximumPoolSize = 5;
    private static long keepAliveTime = 500;
    // 一般推荐使用这样TreadPoolExecutor类来创建线程
    private  static ExecutorService excutors =new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
        TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10) , new ThreadPoolExecutor.DiscardPolicy());


    public static ExecutorService createExecutor(){
        if(excutors == null){
            excutors =Executors.newFixedThreadPool(3);
        }
        return excutors;
    }

    public static ExecutorService createExecutor1(){
        if(excutors == null){
            excutors =Executors.newCachedThreadPool();
        }
        return excutors;
    }

    public static ExecutorService createExecutor2(){
        if(excutors == null){
            excutors =Executors.newSingleThreadExecutor();
        }
        return excutors;
    }

    public static ExecutorService createExecutor3(){
        if(excutors == null){
            excutors =Executors.newScheduledThreadPool(3);
        }
        return excutors;
    }
}
