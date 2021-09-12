package com.li.learn.auxiliaryClass;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch(减法计数器)
 *      0. 需要在线程内调用countDownLatch.countDown()才能实现减法计数
 *      1. 作用：使用这个方法，就是等待计数器变零，然后CountDownLatch.await()就会被唤醒。
 *      2. 理解：就是确保有个语句 在前面动作执行完才能执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"Go out");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        /**
         * 需要计数器归零后，才会向下执行
         */
        countDownLatch.await();
        System.out.println("Close door");
    }
}
