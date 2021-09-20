package com.li.learn.JMM;

import java.util.concurrent.TimeUnit;

/**
 * JMM
 *      1. 背景：Java内存模型，不存在的东西，是一种概念和约定
 *          a. 线程解锁前，必须把共享变量立刻刷回主存
 *          b. 线程加锁前，必须读取主存中的最新值到工作内存中
 *          c. 加锁和解锁是同一把锁
 *      2. 内存交互操作有8种(参考笔记图片)：
 *      3. JMM对这8种指令的使用(参考笔记图片)：
 * 问题：下面就复现了问题-正常线程之间使用的主存变量会不一致(因为每个线程都有一个工作内存)
 */
public class JMMDemo {
    private static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() ->{
            while (num == 0){}
        }).start();

        TimeUnit.SECONDS.sleep(3);
        num = 1;

        System.out.println(num);

    }
}
