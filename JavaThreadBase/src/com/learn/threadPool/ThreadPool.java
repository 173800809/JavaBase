package com.learn.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池：
 *      1. 好处
 *          1.1 提高响应速度(减少创建新线程的时间)
 *          1.2 降低资源消耗(重复利用线程池中的线程，不需要每次都创建)
 *          1.3 便于线程管理
 *              corePoolSize：线程池的大小
 *              maximumPoolSize：最大线程数
 *              keepAliveTime：线程没有任务时最多保持多长时间后会终止
 *      2. 背景
 *          2.1 JDK 5.0起提供了线程池相关API：ExecutorService和Executors
 *          2.2 ExecutorService：真正的线程池接口。常见之类ThreadPoolExecutor
 *              void execute(Runnable command):执行任务/命令，没有返回值，一般用来执行Runnable
 *              <T> Future<T> submit(Callable<T> task):执行任务，有返回值。一般用来执行Callable
 *              void shutdown()：关闭线程池
 *          2.3 Executors:工具类、线程池的工厂类，用于常见并返回不同类型的线程池
 *
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        service.shutdown();
    }
}
class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
