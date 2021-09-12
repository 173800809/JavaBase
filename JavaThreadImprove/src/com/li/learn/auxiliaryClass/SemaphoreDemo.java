package com.li.learn.auxiliaryClass;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore(信号量)
 *      1. 作用：并发限流，控制最大的线程数
 *      2. semaphore.acquire()：获得，假设如果已经满了，等待资源锁被释放为止
 *      3. semaphore.release()：获得，会将当前的信号量释放+1，然后唤醒等待的线程
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() ->{
                try{
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
