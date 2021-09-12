package com.li.learn.auxiliaryClass;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier(加法计数器)
 *      1. 需要在线程内调用cyclicBarrier.await()才能实现 加 计数
 *      2. 当加到parties值后，会执行构造方法的线程。（CyclicBarrier(7,()-> {System.out.println("集卡兑换100万");});）
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()-> {
            System.out.println("集卡兑换100万");
        });

        for (int i = 1; i <= 7; i++) {
            int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集"+temp+"张卡");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
