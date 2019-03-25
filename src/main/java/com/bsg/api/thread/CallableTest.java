package com.bsg.api.thread;

import com.bsg.api.util.CommonsUtil;
import javafx.concurrent.Task;
import org.junit.Test;

import java.util.concurrent.*;

public  class CallableTest  {

    /**
     * //第一种方式
     *         ExecutorService executor = Executors.newCachedThreadPool();
     *         Task task = new Task();
     *         FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
     *         executor.submit(futureTask);
     * //第二种方式
     *         /**
     *         Task task = new Task();
     *         FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
     *         Thread thread = new Thread(futureTask);
     *         thread.start();
     **/
    @Test
    public void CallAbleThreadTest(){
        CallAbleThread callAbleThread = new CallAbleThread();
        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<Integer> result = new FutureTask<>(callAbleThread);
        //2.接收线程运算后的结果
        Integer sum = null;  //FutureTask 可用于 闭锁 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
        new Thread(result).start();
        try {
            sum = result.get();
            System.out.println(sum);
            System.out.println("------------------------------------");
        }catch(Exception e1) {
            e1.printStackTrace();
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                int  i = 1;
                return i;
            }
        };
        Future<Integer> future = (Future<Integer>) executor.submit(task);
        System.out.println("是否成功"+future.isDone());
        try {
            sum  =  future.get();
        }catch (Exception e){

        }
        System.out.println("是否成功"+sum);

    }

    @Test
    public void testCountDownLatch() throws InterruptedException {
        ExecutorService executorService = CommonsUtil.createExecutor();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < countDownLatch.getCount(); i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("1");
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();  // 进入主线程
        System.out.println("主线程"+1);
    }

    @Test
    public void testCyclicBarrier() throws InterruptedException {
        ExecutorService executorService = CommonsUtil.createExecutor();
        CyclicBarrier barrier = new CyclicBarrier(3);
        for (int i = 0; i < barrier.getParties(); i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("1");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    //CyclicBarrier 来限制 每个线程状态为await。才能进行每个线程的后续任务
                    System.out.println("每个线程后续的执行任务");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    //CyclicBarrier 来限制 每个线程状态为await。才能进行每个线程的后续任务
                    System.out.println("每个线程最后后续的执行任务");
                }
            });
        }
        System.out.println("主线程"+1);
    }

}


class CallAbleThread implements Callable<Integer>{
    //可以抛出异常
    @Override
    public Integer call() throws Exception {
        return 1;
    }
}
