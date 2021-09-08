package com.learn.threadState;

/**
 * 线程礼让：
 * 1. 概念：让当前正在执行的线程暂停，但不阻塞。将线程从运行状态转为就绪状态
 * 2. 补充：cpu会重新调度，礼让不一定成功。看CPU心情
 */
public class Yield {
    public static void main(String[] args) {
        MyYield yield = new MyYield();
        new Thread(yield, "a").start();
        new Thread(yield, "b").start();
    }
}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程结束执行");
    }
}