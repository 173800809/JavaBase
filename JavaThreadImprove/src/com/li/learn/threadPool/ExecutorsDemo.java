package com.li.learn.threadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors(线程池工具类，现在不推荐使用)
 *      1. 不推荐使用原因：
 *          a. FixedThreadPool和SingleThreadPool：允许请求队列的长度为Integer.MAX_VALUE,可能会堆积大量的请求，从而导致OOM
 *          b. CachedThreadPool和ScheduledThreadPool: 允许创建的线程数量为Integer.MAX_VALUE,可能会创建大量的线程，从而导致OOM
 *      2. 几个线程的特性
 *          a. Executors.newSingleThreadExecutor 单个线程的线程池
 *          b. Executors.newFixedThreadPool(5) 固定大小的线程池
 *          c. Executors.newCachedThreadPool() 可伸缩的线程池，遇强则强，遇弱则弱
 *          d. Executors.newScheduledThreadPool(3) 定时任务线程池，支持定时或周期执行线程任务
 *      补充：线程池需要关闭：shutdown()和shutdownNow()
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        // 单个线程的线程池
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 固定大小的线程池
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 可伸缩的线程池，遇强则强，遇弱则弱
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 定时任务线程池，支持定时或周期执行线程任务
        ExecutorService threadPool = Executors.newScheduledThreadPool(3);


        try{
            for (int i = 0; i < 50; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
