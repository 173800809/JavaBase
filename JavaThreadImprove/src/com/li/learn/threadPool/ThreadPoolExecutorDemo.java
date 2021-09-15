package com.li.learn.threadPool;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutor(多线程的本质)
 *      1. 入参解释
 *          int corePoolSize ：核心线程池大小
 *          int maximumPoolSize ：最大线程池大小
 *          long keepAliveTime ：超时没人调用该线程就会被释放
 *          TimeUnit unit ：超时时间单位
 *          BlockingQueue<Runnable> workQueue ：阻塞队列
 *          ThreadFactory threadFactory ：线程工厂：创建线程用，一般不用变
 *          RejectedExecutionHandler handler ：拒绝策略
 *      2. 拒绝策略
 *          new ThreadPoolExecutor.AbortPolicy(): 银行满了，还有人进来，不处理这个人的，直接抛出异常
 *          new ThreadPoolExecutor.CallerRunsPolicy(): 哪来的去哪里。比如main函数调用，就让main函数调用
 *          new ThreadPoolExecutor.DiscardPolicy(): 队列满了，丢掉任务，不会抛出异常
 *          new ThreadPoolExecutor.DiscardOldestPolicy(): 队列满了，尝试去和最早的竞争，也不会抛出异常
 *      3. 池的最大的大小如何去设置，最大线程数该如何定义
 *          a. CPU 密集型：CPU是几核，就填几，可以保持CPU的效率最高
 *          b. IO 密集型：设置为两倍。判断程序中十分耗IO的线程，如果有10个耗IO的线程，就把最大线程设置为20
 *      4.线程池的最大承载能力：队列+最大线程数(银行服务窗口数量+候客区数量)
 *
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 2;
        long keepAliveTime = 2;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        /**
         * 多线程的关键
         */
        ExecutorService threadPool = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);

        try{
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "执行");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
